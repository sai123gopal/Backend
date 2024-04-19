package com.saigopa.travel.Travel.Services;

import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mongodb.client.result.UpdateResult;
import com.saigopa.travel.Travel.Helpers.JwtUtil;
import com.saigopa.travel.Travel.Models.User.SignUpModel;
import com.saigopa.travel.Travel.Models.User.UserDataModel;
import com.saigopa.travel.Travel.Repositories.UsersDbRepo;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserServices {

    @Autowired
    UsersDbRepo usersDbRepo;

    @Autowired
    MongoTemplate mongoTemplate;

    public UserDataModel getUserDetailsFromToken(String bearerToken) throws Exception {
        String token = bearerToken.substring(7);
        String userId;
        try {
            userId = JwtUtil.extractUserId(token);
            return usersDbRepo.findByuserId(userId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public UserDataModel getUserByEmail(String email) {
        return usersDbRepo.findByEmail(email);
    }

    public void saveSignUpUserData(SignUpModel signUpDetails) throws Exception {
        try {
            UserDataModel userDataModel = new UserDataModel();
            userDataModel.setCreatedAt(new Date());
            userDataModel.setEmail(signUpDetails.getEmail());
            userDataModel.setFirstName(signUpDetails.getFirstName());
            userDataModel.setLastName(signUpDetails.getLastName());
            userDataModel.setIsEmailVerified(false);
            userDataModel.setPassword(signUpDetails.getPassword());
            userDataModel.setUpdatedAt(new Date());
            userDataModel.setDeviceData(signUpDetails.getDeviceData());

            usersDbRepo.save(userDataModel);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void saveLoginUserData(UserDataModel userData) throws Exception {
        try {
            userData.setJWTToken(JwtUtil.generateToken(userData.getId()));
            Query query = new Query(Criteria.where("_id").is(userData.getId()));
            Update update = new Update();
            update.set("JWTToken", userData.getJWTToken());
            update.set("updatedAt", new Date());

            UpdateResult result = mongoTemplate.updateFirst(query, update, UserDataModel.class);
            if (result == null) {
                throw new Exception("Update failed");
            }
        } catch (Exception e) {
            throw e;
        }

    }

    public void updateEmailVerified(String userId) throws Exception{
       try{
        Query query = new Query(Criteria.where("_id").is(userId));
        Update update = new Update();
        update.set("isEmailVerified", true);
        update.set("updatedAt", new Date());

        UpdateResult result = mongoTemplate.updateFirst(query, update, UserDataModel.class);
        if (result == null) {
            throw new Exception("Verification failed");
        }
       }catch(Exception e){
            throw e;
       }
    }

    public void sendVerificationEmail(UserDataModel userData) throws Exception{
        Properties props = new Properties();

        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.properties.mail.debug", "false");

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setUsername(Dotenv.load().get("mail_id"));
        sender.setPassword(Dotenv.load().get("mail_password"));
        sender.setJavaMailProperties(props);

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo("sai.gopalsai143@gmail.com");
        helper.setSubject("Verify Email Id for Travel app");
        helper.setText(
                "Hi " + userData.getFirstName() + "\nPlease verify email Id before loging In \n\n" + "Link : " +
                        ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString() + "/verifyEmail?id="
                        + userData.getId() + "\n\n Thank you");

        sender.send(message);
    }

}
