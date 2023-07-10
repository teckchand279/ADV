package com.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
	private String message;
	private LocalDate timeStamp;
	
	public ApiResponse(String mesg) {
		this.message = mesg;
		this.timeStamp = LocalDate.now();
	}

}
