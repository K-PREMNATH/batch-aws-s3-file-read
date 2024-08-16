package com.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;

@Service
public class S3Service {

    private final S3Client s3Client;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void readFileFromS3(String bucketName, String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        long startTime = System.currentTimeMillis();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                s3Client.getObject(getObjectRequest)))) {

            String line;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(100);
                } catch (Exception  e) {
                    e.printStackTrace();
                }
            });
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            future.join();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read file from S3", e);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("ST   :"+startTime);
        System.out.println("ET   :"+endTime);
        System.out.println("ET-ST:"+(endTime-startTime));
    }
}
