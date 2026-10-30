package com.project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

//do validation using sts validation package
//in dto
//@Valid annotation before the request obj use in controller
@Data
public class StudentRequest {

	@NotBlank(message = "name is required")
	@Size(min = 3, max = 30)
	private String name;

	@Email
	@NotBlank(message = "email is required")
	private String email;
}
