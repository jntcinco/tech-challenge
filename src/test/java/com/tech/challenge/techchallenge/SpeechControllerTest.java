package com.tech.challenge.techchallenge;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.tech.challenge.techchallenge.controller.SpeechController;
import com.tech.challenge.techchallenge.model.Speech;
import com.tech.challenge.techchallenge.repository.SpeechRepository;

@WebMvcTest(SpeechController.class)
public class SpeechControllerTest extends MockMvcResultMatchers {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	SpeechRepository speechRepo;

	Speech S1 = new Speech().id(1l).actualText("test actual").author("test author").subjectText("test subject")
								   .createdDate(Date.valueOf("2022-04-06"));
	Speech S2 = new Speech().id(1l).actualText("test actual2").author("test author2").subjectText("test subject2")
								   .createdDate(Date.valueOf("2022-04-06"));

	@Test
	public void getAll_success() throws Exception {

		List<Speech> speeches = new ArrayList<>(Arrays.asList(S1, S2));
		Mockito.when(speechRepo.findAll()).thenReturn(speeches);
		mockMvc.perform(MockMvcRequestBuilders.get("/speeches").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[1].actualText", Matchers.is("test actual2")));
	}
}
