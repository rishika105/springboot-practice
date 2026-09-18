package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor  //to initialize it with values like new StudentDTO("1", "dnan, "")
public class StudentDTO {
	private Integer id;
    private String name;
    private String email;
}
