package de.devsurf.hacking.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
//import org.springframework.security.core.userdetails.User;
import org.springframework.util.ErrorHandler;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.accept.RequestedContentTypeResolverBuilder;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URI;

@EnableWebFlux
//@EnableWebFluxSecurity
//@EnableSwagger2
@Configuration
//@EnableAutoConfiguration
public class WebfluxConfig implements WebFluxConfigurer {

    @Value("${amazon.dynamodb.endpoint}")
    private String dynamoEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String accessKey;

    @Value("${amazon.aws.secretkey}")
    private String secretKey;

    @Value("${amazon.dynamodb.region}")
    private String dynamoRegion;

    /*@Bean
    public MapReactiveUserDetailsService userDetailsService() {
        return new MapReactiveUserDetailsService(User.builder().build());
    }*/

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(ApiInfo.DEFAULT);
    }

    private AwsCredentialsProvider amazonAWSCredentialsProvider() {
        return StaticCredentialsProvider.create(awsCredentials());
    }

    private AwsCredentials awsCredentials() {
        return new AwsCredentials() {
            @Override
            public String accessKeyId() {
                return accessKey;
            }

            @Override
            public String secretAccessKey() {
                return secretKey;
            }
        };
    }

    @Bean
    public DynamoDbAsyncClient dynamoDbAsyncClient()  {
        return DynamoDbAsyncClient.builder()
                .region(Region.of(dynamoRegion))
                .endpointOverride(URI.create(dynamoEndpoint))
                .credentialsProvider(amazonAWSCredentialsProvider())
                .build();
    }
}
