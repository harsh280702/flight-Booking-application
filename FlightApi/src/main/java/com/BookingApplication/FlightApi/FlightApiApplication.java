package com.BookingApplication.FlightApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories
public class FlightApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightApiApplication.class, args);
	}

}
