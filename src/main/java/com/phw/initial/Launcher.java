package com.phw.initial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.phw.toolkit" })
public class Launcher {

	public static void main(String[] args) {
		SpringApplication.run(Launcher.class, args);
	}

}
