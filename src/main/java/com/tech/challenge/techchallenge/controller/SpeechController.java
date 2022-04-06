package com.tech.challenge.techchallenge.controller;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/speeches/subjectText/{subjectText}")
	public List<Speech> getBySubjectText(@PathVariable String subjectText) {
		return speechRepo.findBySubjectText(subjectText);
	}
	
	@GetMapping("/speeches/actualText/{actualText}")
	public List<Speech> getByActualText(@PathVariable String actualText) {
		return speechRepo.findByActualText(actualText);
	}
	
	@GetMapping("/speeches/createdDate/{createdDate}")
	public List<Speech> getByCreatedDate(@PathVariable String createdDate) {
		return speechRepo.findByCreatedDate(createdDate);
	}

	@PostMapping("/speeches")
	public Speech saveSpeech(@RequestBody SpeechObject speechReq) {
		Speech speech = new Speech().actualText(speechReq.getActualText()).subjectText(speechReq.getSubjectText()).createdDate(Date.valueOf(speechReq.getCreatedDate()));
		return speechRepo.save(speech);
	}

	@PutMapping("/speeches/{id}")
	public Speech editSpeech(@RequestBody SpeechObject newSpeech, @PathVariable Long id) {

		return speechRepo.findById(id).map(speech -> {
			speech.setActualText(newSpeech.getActualText());
			speech.setSubjectText(newSpeech.getSubjectText());
			speech.setModifiedDate(Date.valueOf(newSpeech.getModifiedDate()));
			return speechRepo.save(speech);
		}).orElseGet(() -> {
			Speech speech = new Speech().actualText(newSpeech.getActualText()).subjectText(newSpeech.getSubjectText())
										.modifiedDate(Date.valueOf(newSpeech.getModifiedDate()));
			speech.setId(id);
			return speechRepo.save(speech);
		});
	}

	@DeleteMapping("/speeches/{id}")
	public void deleteSpeech(@PathVariable Long id) {
		speechRepo.deleteById(id);
	}
}
