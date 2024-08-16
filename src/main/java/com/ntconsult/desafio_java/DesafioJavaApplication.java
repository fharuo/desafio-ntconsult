package com.ntconsult.desafio_java;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioJavaApplication.class, args);
	}

	@PostConstruct
	public void buildInit() {
		System.out.println("Running and Up!");
	}
}
