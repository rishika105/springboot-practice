package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // to initialize it with values like new StudentDTO("1", "dnan, "")
@NoArgsConstructor
public class StudentDTO {
	private Long id;
	private String name;
	private String email;
}
