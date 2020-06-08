package com.amdocs.oss.ruleframework;

import com.amdocs.oss.ruleframework.config.HttpClientConfig;
import com.amdocs.oss.ruleframework.service.RuleService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableBinding(Source.class)
//@Import(HttpClientConfig.class)
@EnableEurekaClient
@RefreshScope
@OpenAPIDefinition(info = @Info(title = "Rule Framework"))
public class RuleFrameworkApplication {


    @Autowired
    RuleService ruleService;
    public static void main(String[] args) {
        SpringApplication.run(RuleFrameworkApplication.class, args);
    }

    @PostConstruct
    private void init() {
        ruleService.addRules();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }


}
