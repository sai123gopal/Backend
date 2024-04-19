package com.saigopa.travel.Travel;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class TravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}

	@Bean
	public HttpExchangeRepository htttpTraceRepository() {
		return new InMemoryHttpExchangeRepository();
	}

	@Component
	class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {

		@Override
		public void onApplicationEvent(ContextRefreshedEvent event) {
			String serverPort = event.getApplicationContext().getEnvironment().getProperty("server.port");
			String contextPath = event.getApplicationContext().getEnvironment()
					.getProperty("server.servlet.context-path", "");
			String host = getHost();

			String url = UriComponentsBuilder.newInstance()
					.scheme("http")
					.host(host)
					.port(serverPort)
					.path(contextPath)
					.build()
					.toUriString();

			System.out.println("Application URL: " + url);
		}
	}

	private String getHost() {
		try {
			// Get the local host address
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// If unable to get the local host address, default to localhost
			return "localhost";
		}
	}

}
