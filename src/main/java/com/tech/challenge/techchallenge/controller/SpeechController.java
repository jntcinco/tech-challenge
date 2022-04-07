package com.tech.challenge.techchallenge.controller;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tech.challenge.techchallenge.exception.SpeechNotFoundException;
import com.tech.challenge.techchallenge.model.Speech;
import com.tech.challenge.techchallenge.pojo.SpeechObject;
import com.tech.challenge.techchallenge.repository.SpeechRepository;

@RestController
public class SpeechController {

	private static final Logger LOG = LoggerFactory.getLogger(SpeechController.class);
	
	private final SpeechRepository speechRepo;

	public SpeechController(SpeechRepository speechRepo) {
		this.speechRepo = speechRepo;
	}

	@GetMapping("/speeches")
	public List<Speech> getAll() {
		return speechRepo.findAll();
	}
	
	@GetMapping("/speeches/author/{author}")
	public ResponseEntity<List<Speech>> getByAuthorName(@PathVariable String author) {
		List<Speech> speeches = speechRepo.findByAuthor(author);
		return new ResponseEntity<>(speeches, HttpStatus.OK);
	}
	
	@GetMapping("/speeches/subjectText/{subjectText}")
	public ResponseEntity<List<Speech>> getBySubjectText(@PathVariable String subjectText) {
		List<Speech> speeches = speechRepo.findBySubjectText(subjectText);
		return new ResponseEntity<>(speeches, HttpStatus.OK);
	}
	
	@GetMapping("/speeches/actualText/{actualText}")
	public ResponseEntity<List<Speech>> getByActualText(@PathVariable String actualText) {
		List<Speech> speeches = speechRepo.findByActualText(actualText);
		return new ResponseEntity<>(speeches, HttpStatus.OK);
	}
	
	@GetMapping("/speeches/createdDate/{createdDate}")
	public ResponseEntity<List<Speech>> getByCreatedDate(@PathVariable String createdDate) {
		List<Speech> speeches = speechRepo.findByCreatedDate(Date.valueOf(createdDate));
		return new ResponseEntity<>(speeches, HttpStatus.OK);
	}

	@PostMapping("/speeches")
	public Speech saveSpeech(@RequestBody SpeechObject speechReq) {
		Speech speech = new Speech().actualText(speechReq.getActualText()).subjectText(speechReq.getSubjectText()).author(speechReq.getAuthor()).createdDate(Date.valueOf(speechReq.getCreatedDate()));
		return speechRepo.save(speech);
	}

	@PutMapping("/speeches")
	public Speech editSpeech(@RequestBody SpeechObject newSpeech) {
		Speech speech = null;
		
		Long id = newSpeech.getId() != null ? Long.valueOf(newSpeech.getId()) : null;
		if(id == null) {
			throw new SpeechNotFoundException("Speech ID is null.");
		}
		
		try {
			speech = speechRepo.findById(id).get();
			speech.setActualText(newSpeech.getActualText());
			speech.setSubjectText(newSpeech.getSubjectText());
			speech.setAuthor(newSpeech.getAuthor());
			speech.setModifiedDate(Date.valueOf(newSpeech.getModifiedDate()));
		} catch(NoSuchElementException e) {
			throw new SpeechNotFoundException("There's no record to update. Id " + id + " not found.");
		}
		return speechRepo.save(speech);
	}

	@DeleteMapping("/speeches/{id}")
	public void deleteSpeech(@PathVariable Long id) {
		try {
			speechRepo.findById(id).get();
		} catch(NoSuchElementException e) {
			throw new SpeechNotFoundException("There's no record to delete. Id " + id + " not found.");
		}
		speechRepo.deleteById(id);
	}
}
