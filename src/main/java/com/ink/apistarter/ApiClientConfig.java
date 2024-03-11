package com.ink.apistarter;

import com.ink.apistarter.client.CallApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api.client")
@Data
@ComponentScan
public class ApiClientConfig {

    private String AccessKey;

    private String SecretKey;

    private String Token;

    @Bean
    public CallApiClient callApiClient(){
        return new CallApiClient(AccessKey, SecretKey, Token);
    }

}
