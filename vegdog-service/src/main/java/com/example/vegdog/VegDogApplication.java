package com.example.vegdog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VegDogApplication {

	public static void main(String[] args) {
		SpringApplication.run(VegDogApplication.class, args);
	}

}
