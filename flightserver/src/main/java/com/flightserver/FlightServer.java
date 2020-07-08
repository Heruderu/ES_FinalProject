package com.flightserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class FlightServer {

	public static void main(String[] args) {
		SpringApplication.run(FlightServer.class, args);
	}

}
