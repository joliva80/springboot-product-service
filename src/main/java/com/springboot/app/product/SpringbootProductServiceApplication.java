package com.springboot.app.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootProductServiceApplication.class, args);
	}

}
