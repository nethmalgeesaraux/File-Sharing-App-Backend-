package com.example.File_Sharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class FileSharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileSharingApplication.class, args);
	}

}
