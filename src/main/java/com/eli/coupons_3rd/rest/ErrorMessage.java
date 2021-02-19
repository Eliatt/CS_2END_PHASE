package com.eli.coupons_3rd.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

	public ErrorMessage(Exception e) {
		message = e.getMessage();
	}

	private String message;

}
