package com.springboot.app.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.springboot.app.commonslib.model.entity"}) // Import the entity beans from commons-lib jar package files
public class SpringbootProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootProductServiceApplication.class, args);
	}

}
