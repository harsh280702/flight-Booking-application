package com.BookingApplication.ServicesResgsitry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServicesResgsitryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicesResgsitryApplication.class, args);
	}

}
