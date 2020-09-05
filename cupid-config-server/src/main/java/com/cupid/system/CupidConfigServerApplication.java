package com.cupid.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CupidConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CupidConfigServerApplication.class, args);
	}

}
