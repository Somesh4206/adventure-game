package com.adventure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdventureGameApplication {
    public static void main(String[] args) {
        // Convert Render's DATABASE_URL format to JDBC format
        String databaseUrl = System.getenv("DATABASE_URL");
        if (databaseUrl != null && databaseUrl.startsWith("postgres://")) {
            databaseUrl = databaseUrl.replace("postgres://", "jdbc:postgresql://");
            System.setProperty("spring.datasource.url", databaseUrl);
        }
        
        SpringApplication.run(AdventureGameApplication.class, args);
    }
}
