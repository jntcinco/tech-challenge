package com.tech.challenge.techchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Enables auto configuration, component scanning, and property support.
 * @author cincoj
 *
 */
@SpringBootApplication
public class TechChallengeApplication {

	/**
	 * entry-point method
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TechChallengeApplication.class, args);
	}

}
