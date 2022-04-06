package com.tech.challenge.techchallenge.exception;

public class SpeechNotFoundException extends RuntimeException {

	public SpeechNotFoundException(String message, Long id) {
		super(message + " " + id);
	}
}
