package com.tech.challenge.techchallenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.challenge.techchallenge.model.Speech;

public interface SpeechRepository extends JpaRepository<Speech, Long> {

	public List<Speech> findByActualText(String actualText);
	public List<Speech> findBySubjectText(String subjectText);
	public List<Speech> findByCreatedDate(String subjectText);
	
}
