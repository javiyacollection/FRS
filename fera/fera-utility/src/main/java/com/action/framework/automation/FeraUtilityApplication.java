package com.action.framework.automation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy
@EnableBinding(Sink.class)
@EnableEurekaClient
public class FeraUtilityApplication {

	/**
	 * don't change anything to this file. This file is just for boot.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		SpringApplication.run(FeraUtilityApplication.class, args);
		System.out.println("AutomationUtilityApplication execution done");
	}

}
