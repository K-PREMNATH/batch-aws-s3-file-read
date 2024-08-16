package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import java.time.Duration;
import software.amazon.awssdk.http.apache.ApacheHttpClient;

@Configuration
public class AwsConfig {

    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(
                "ACCESS-KEY",
                "SECRET-KEY");

        ApacheHttpClient httpClient = (ApacheHttpClient) ApacheHttpClient.builder()
                .socketTimeout(Duration.ofMillis(40))
                .connectionTimeout(Duration.ofMillis(40))
                .build();

        return S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(Region.AP_SOUTHEAST_1)
                .httpClient(httpClient)
                .build();
    }
}
