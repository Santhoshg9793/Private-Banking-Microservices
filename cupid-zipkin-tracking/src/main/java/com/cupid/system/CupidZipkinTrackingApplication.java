package com.cupid.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class CupidZipkinTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CupidZipkinTrackingApplication.class, args);
	}

}
