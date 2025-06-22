package com.saigopa.travel.Travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.google.firebase.FirebaseApp;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class TravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
		FirebaseApp.initializeApp();
	}

}
