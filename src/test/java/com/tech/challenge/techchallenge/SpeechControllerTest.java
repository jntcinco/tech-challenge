package com.tech.challenge.techchallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.challenge.techchallenge.controller.SpeechController;
import com.tech.challenge.techchallenge.exception.SpeechNotFoundException;
import com.tech.challenge.techchallenge.model.Speech;
import com.tech.challenge.techchallenge.pojo.SpeechObject;
import com.tech.challenge.techchallenge.repository.SpeechRepository;

@WebMvcTest(SpeechController.class)
public class SpeechControllerTest extends MockMvcResultMatchers {

	private static final String SUBJECT_TEXT				= "test subject3";
	private static final String ACTUAL_TEXT					= "test actual2";
	private static final String AUTHOR_NAME					= "test author";
	private static final String CREATED_DATE				= "2022-04-06";

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
    ObjectMapper mapper;

	@MockBean
	SpeechRepository speechRepo;

	Speech S1 = new Speech().id(1l).actualText("test actual").author("test author").subjectText("test subject")
								   .createdDate(Date.valueOf(CREATED_DATE));
	Speech S2 = new Speech().id(2l).actualText("test actual2").author("test author2").subjectText("test subject2")
								   .createdDate(Date.valueOf(CREATED_DATE));
	Speech S3 = new Speech().id(3l).actualText("test actual2").author("test author").subjectText("test subject3")
			   					   .createdDate(Date.valueOf(CREATED_DATE));

	@Test
	public void findAll_success() throws Exception {

		List<Speech> speeches = new ArrayList<>(Arrays.asList(S1,S2,S3));
		Mockito.when(speechRepo.findAll()).thenReturn(speeches);
		mockMvc.perform(MockMvcRequestBuilders.get("/speeches").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(3)))
				.andExpect(jsonPath("$[1].actualText", Matchers.is(ACTUAL_TEXT)))
				.andExpect(jsonPath("$[0].author", Matchers.is(AUTHOR_NAME)))
				.andExpect(jsonPath("$[2].subjectText", Matchers.is(SUBJECT_TEXT)));
	}
	
	@Test
	public void findByAuthor_success() throws Exception {

		List<Speech> speeches = new ArrayList<>(Arrays.asList(S1,S3));
		Mockito.when(speechRepo.findByAuthor(AUTHOR_NAME)).thenReturn(speeches);
		mockMvc.perform(MockMvcRequestBuilders.get("/speeches/author/" + AUTHOR_NAME).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].author", Matchers.is(AUTHOR_NAME)))
				.andExpect(jsonPath("$[1].author", Matchers.is(AUTHOR_NAME)));
	}
	
	@Test
	public void findByActualText_success() throws Exception {

		List<Speech> speeches = new ArrayList<>(Arrays.asList(S2,S3));
		Mockito.when(speechRepo.findByActualText(ACTUAL_TEXT)).thenReturn(speeches);
		mockMvc.perform(MockMvcRequestBuilders.get("/speeches/actualText/" + ACTUAL_TEXT).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].actualText", Matchers.is(ACTUAL_TEXT)))
				.andExpect(jsonPath("$[1].actualText", Matchers.is(ACTUAL_TEXT)));
	}
	
	@Test
	public void findBySubjectText_success() throws Exception {

		List<Speech> speeches = new ArrayList<>(Arrays.asList(S3));
		Mockito.when(speechRepo.findBySubjectText(SUBJECT_TEXT)).thenReturn(speeches);
		mockMvc.perform(MockMvcRequestBuilders.get("/speeches/subjectText/" + SUBJECT_TEXT).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].subjectText", Matchers.is(SUBJECT_TEXT)));
	}
	
	@Test
	public void findByCreatedDate_success() throws Exception {

		List<Speech> speeches = new ArrayList<>(Arrays.asList(S1,S2,S3));
		Mockito.when(speechRepo.findByCreatedDate(Date.valueOf(CREATED_DATE))).thenReturn(speeches);
		mockMvc.perform(MockMvcRequestBuilders.get("/speeches/createdDate/" + CREATED_DATE).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(3)))
				.andExpect(jsonPath("$[1].actualText", Matchers.is(ACTUAL_TEXT)));
	}
	
	@Test
	public void save_success() throws Exception {
		Mockito.when(speechRepo.save(S1)).thenReturn(S1);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/speeches")
	            									.contentType(MediaType.APPLICATION_JSON)
	            									.accept(MediaType.APPLICATION_JSON)
	            									.content(this.mapper.writeValueAsString(S1));
		
		mockMvc.perform(mockRequest).andExpect(status().isOk());
	}
	
	@Test
	public void update_success() throws Exception {
		SpeechObject updateSpeech = new SpeechObject();
		updateSpeech.setId("1");
		updateSpeech.setActualText(ACTUAL_TEXT);
		updateSpeech.setAuthor(AUTHOR_NAME);
		updateSpeech.setSubjectText(SUBJECT_TEXT);
		updateSpeech.setModifiedDate(CREATED_DATE);
		Mockito.when(speechRepo.findById(S1.getId())).thenReturn(Optional.of(S1));
		Mockito.when(speechRepo.save(S1)).thenReturn(S1);
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/speeches")
	            									.contentType(MediaType.APPLICATION_JSON)
	            									.accept(MediaType.APPLICATION_JSON)
	            									.content(this.mapper.writeValueAsString(updateSpeech));
		
		mockMvc.perform(mockRequest).andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.notNullValue()))
        .andExpect(jsonPath("$.actualText", Matchers.is(ACTUAL_TEXT)));
	}
	
	@Test
	public void update_recordNotFound() throws Exception {
		SpeechObject updateSpeech = new SpeechObject();
		updateSpeech.setId("2");
		updateSpeech.setActualText(ACTUAL_TEXT);
		updateSpeech.setAuthor(AUTHOR_NAME);
		updateSpeech.setSubjectText(SUBJECT_TEXT);
		updateSpeech.setModifiedDate(CREATED_DATE);
	    Mockito.when(speechRepo.findById(S1.getId())).thenReturn(null);
		Mockito.when(speechRepo.save(S1)).thenReturn(S1);
	    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/speeches")
	            														  .contentType(MediaType.APPLICATION_JSON)
	            														  .accept(MediaType.APPLICATION_JSON)
	            														  .content(this.mapper.writeValueAsString(updateSpeech));

	    mockMvc.perform(mockRequest).andExpect(status().isNotFound())
	            				    .andExpect(result -> assertTrue(result.getResolvedException() instanceof SpeechNotFoundException))
	            				    .andExpect(result -> assertEquals("There's no record to update. Id " + updateSpeech.getId() + " not found.", result.getResolvedException().getMessage()));
	}
	
	@Test
	public void delete_success() throws Exception {
	    Mockito.when(speechRepo.findById(S1.getId())).thenReturn(Optional.of(S1));

	    mockMvc.perform(MockMvcRequestBuilders
	            .delete("/speeches/1")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void delete_recordNotFound() throws Exception {
	    Mockito.when(speechRepo.findById(S1.getId())).thenReturn(Optional.of(S1));
	    String id = "2";
	    mockMvc.perform(MockMvcRequestBuilders
	            .delete("/speeches/"+id)
	            .contentType(MediaType.APPLICATION_JSON))
	    		.andExpect(status().isNotFound())
	            .andExpect(result -> assertTrue(result.getResolvedException() instanceof SpeechNotFoundException))
	            .andExpect(result -> assertEquals("There's no record to delete. Id " + id + " not found.", result.getResolvedException().getMessage()));
	}
}
