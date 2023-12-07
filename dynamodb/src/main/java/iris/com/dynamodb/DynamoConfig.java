package iris.com.dynamodb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;

import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class DynamoConfig {
    Region region = Region.US_EAST_1;

    AwsCredentials credentials = AwsBasicCredentials.create("key", "1234");

    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                .region(region)
                .endpointOverride(URI.create("http://localhost:4566"))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

}
