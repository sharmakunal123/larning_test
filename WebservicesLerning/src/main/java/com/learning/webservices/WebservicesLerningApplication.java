package com.learning.webservices;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class WebservicesLerningApplication {
// https://www.google.com/search?q=microservices+with+spring+boot&oq=Microservices+&aqs=chrome.5.69i57j0l5.7166j0j7&sourceid=chrome&ie=UTF-8
	public static void main(String[] args) {
		SpringApplication.run(WebservicesLerningApplication.class, args);
		
		FirebaseOptions options;
		try {
			options = new FirebaseOptions.Builder()
				    .setCredentials(GoogleCredentials.getApplicationDefault())
				    .setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")
				    .build();
			FirebaseApp.initializeApp(options);
			FirebaseApp.initializeApp();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
