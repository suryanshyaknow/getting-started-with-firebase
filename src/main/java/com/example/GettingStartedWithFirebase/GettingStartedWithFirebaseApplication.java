package com.example.GettingStartedWithFirebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@SpringBootApplication
public class GettingStartedWithFirebaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(GettingStartedWithFirebaseApplication.class, args);
    }

    @PostConstruct
    public static void initializeFirebase() throws IOException {
        ClassLoader classLoader = GettingStartedWithFirebaseApplication.class.getClassLoader();
        InputStream serviceAccountStream = classLoader.getResourceAsStream("service-account-pvt-key.json");

        assert serviceAccountStream != null;
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                .build();

        FirebaseApp.initializeApp(options);

    }


}
