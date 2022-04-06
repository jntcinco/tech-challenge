package com.tech.challenge.techchallenge;

import java.sql.Date;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tech.challenge.techchallenge.model.Speech;
import com.tech.challenge.techchallenge.repository.SpeechRepository;

/**
 * Preload class. 
 * @author cincoj
 *
 */

@Configuration
public class LoadDatabase {

	private static final Logger LOG = LoggerFactory.getLogger(LoadDatabase.class);
	
	/**
	 * Spring boot will run this bean once the application context is loaded.
	 * This will request a copy of the SpeechRepository and it will create to entity and store.
	 * 
	 * @param repo
	 * @return
	 */
	
	@Bean 
	CommandLineRunner initDatabase(SpeechRepository repo) {
		
		LocalDate dateNow = LocalDate.now();
		Date sqlDate = Date.valueOf(dateNow);
		return artgs -> {
			LOG.info("Load " + repo.save(new Speech().actualText("test speech").subjectText("test subject").createdDate(sqlDate)));
		};
	}
}
