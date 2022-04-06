package com.tech.challenge.techchallenge.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.challenge.techchallenge.model.Speech;

public interface SpeechRepository extends JpaRepository<Speech, Long> {

	public List<Speech> findByActualText(String actualText);
	public List<Speech> findBySubjectText(String subjectText);
	public List<Speech> findByCreatedDate(Date createdDate);
	public List<Speech> findByAuthor(String author);
	
}
