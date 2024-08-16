package com;

import com.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchAwsS3FileReadApplication implements CommandLineRunner {

	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(BatchAwsS3FileReadApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String bucketName = "incoming-workspace";
		String fileName = "sample.txt";

		s3Service.readFileFromS3(bucketName,fileName);

	}
}
