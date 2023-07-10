package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class CreateNewCourse {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	@NotBlank(message = "tile can't be blank")
	@NotNull(message = "tile can't be null")
	private String title;
	@PastOrPresent
	private LocalDate startDate;
	@FutureOrPresent
	private LocalDate endDate;	
	private double fees;
	private double minScare;

}
