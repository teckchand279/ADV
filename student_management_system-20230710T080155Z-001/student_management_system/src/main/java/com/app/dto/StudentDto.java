package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentDto {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	private String firstName;

	private String lastName;

	private String email;
	
	private double score;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String courseTitle;
}
