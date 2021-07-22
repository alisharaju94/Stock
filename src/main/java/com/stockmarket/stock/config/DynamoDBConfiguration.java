package com.stockmarket.stock.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.stockmarket.stock.repo")
public class DynamoDBConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDBConfiguration.class);
    private static final String DYNAMO_DB_MODE_REMOTE = "local";

    @Value("${local.amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${local.amazon.dynamodb.credentials.access-key}")
    private String amazonAWSAccessKey;

    @Value("${local.amazon.dynamodb.credentials.secret-key}")
    private String amazonAWSSecretKey;

    @Value("${amazon.dynamodb.signingregion}")
    private String amazonAWSRegion;

    @Value("${amazon.dynamodb.mode}")
    private String getAmazonDynamoDBMode;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        LOGGER.info("AmazonDynamoDB starting");
        AmazonDynamoDBClientBuilder amazonDynamoDB = AmazonDynamoDBClientBuilder.standard();
        if (DYNAMO_DB_MODE_REMOTE.equals(getAmazonDynamoDBMode)) {
            LOGGER.info("AmazonDynamoDB starting" + DYNAMO_DB_MODE_REMOTE);
            amazonDynamoDB.setRegion(amazonAWSRegion);
            amazonDynamoDB.setCredentials(new InstanceProfileCredentialsProvider(false));
        } else {
            LOGGER.info("AmazonDynamoDB starting local");
            amazonDynamoDB.setEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, amazonAWSRegion));
            amazonDynamoDB.setCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey)));

        }
        return amazonDynamoDB.build();
    }
}
