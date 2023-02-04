package com.aqms.sensorplot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// Sensor Plot Application

@SpringBootApplication
@EnableDiscoveryClient
public class SensorplotServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorplotServiceApplication.class, args);
	}

}
