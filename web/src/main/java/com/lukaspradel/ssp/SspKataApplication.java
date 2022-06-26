package com.lukaspradel.ssp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lukaspradel.ssp")
public class SspKataApplication {

	public static void main(String[] args) {
		SpringApplication.run(SspKataApplication.class, args);
	}

}
