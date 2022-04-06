package com.tech.challenge.techchallenge.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="speech")
public class Speech {

	private @Id @GeneratedValue Long id;
	private String actualText;
	private String subjectText;
	private String author;
	private Date createdDate;
	private Date modifiedDate;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public Speech actualText(String actualText) {
		this.actualText = actualText;
		return this;
	}
	
	public Speech subjectText(String subjectText) {
		this.subjectText = subjectText;
		return this;
	}
	
	public Speech author(String author) {
		this.author = author;
		return this;
	}
	
	public Speech createdDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}
	
	public Speech modifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}
}
