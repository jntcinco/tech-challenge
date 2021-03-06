package com.tech.challenge.techchallenge.pojo;

public class SpeechObject {

	private String id;
	private String author;
	private String actualText;
	private String subjectText;
	private String createdDate;
	private String modifiedDate;
	
	public String getActualText() {
		return actualText;
	}
	public void setActualText(String actualText) {
		this.actualText = actualText;
	}
	public String getSubjectText() {
		return subjectText;
	}
	public void setSubjectText(String subjectText) {
		this.subjectText = subjectText;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
