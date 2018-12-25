package com.example.bookstore.businessHandler;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @author amankapoor
 *	This is a POJO which holds the data for the Media Coverage objects returned.
 */
public class MediaCoverageBO {
	
	@JsonIgnore
	private String userId;
	
	@JsonIgnore
	private String id;
	
	private String title;
	
	private String body;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "MediaCoverage [userId=" + userId + ", id=" + id + ", title=" + title + ", body=" + body + "]";
	}
}
