package com.tech.challenge.techchallenge;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tech.challenge.techchallenge.exception.SpeechNotFoundException;

/**
 * When an SpeechNotFoundException is thrown, this extra tidbit of Spring MVC configuration is used to render an HTTP 404:
 * @author cincoj
 *
 */
@ControllerAdvice
public class SpeechNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(SpeechNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String speechNotFoundHandler(SpeechNotFoundException se) {
		return se.getMessage();
	}
}
