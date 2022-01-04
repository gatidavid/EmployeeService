package com.fount.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {

	private String status;
	private String message;
	private String module;
	private String date;
}
