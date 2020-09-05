package com.cupid.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CupidRegistryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CupidRegistryServerApplication.class, args);
	}

}
