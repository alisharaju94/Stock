package com.stockmarket.stock.config;

import com.amazonaws.auth.AWSCredentialsProvider;
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
	private static final String DYNAMO_DB_MODE_REMOTE = "remote";

	@Value("${amazon.dynamodb.endpoint}")
	private String amazonDynamoDBEndpoint;

	@Value("${amazon.dynamodb.credentials.access-key}")
	private String amazonAWSAccessKey;

	@Value("${amazon.dynamodb.credentials.secret-key}")
	private String amazonAWSSecretKey;

	@Value("${amazon.dynamodb.signingregion}")
	private String amazonAWSRegion;

	@Value("${amazon.dynamodb.mode}")
	private String amazonDynamoDBMode;

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		AmazonDynamoDBClientBuilder amazonDynamoDB = AmazonDynamoDBClientBuilder.standard();
		LOGGER.info("AmazonDynamoDB starting in {}", amazonDynamoDBMode);
		AWSCredentialsProvider creds = new AWSStaticCredentialsProvider(
				new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey));
		amazonDynamoDB.withCredentials(creds);
		if (DYNAMO_DB_MODE_REMOTE.equals(amazonDynamoDBMode)) {
			amazonDynamoDB.withRegion(amazonAWSRegion);
		} else {
			amazonDynamoDB.setEndpointConfiguration(
					new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, amazonAWSRegion));
		}
		return amazonDynamoDB.build();
	}
}