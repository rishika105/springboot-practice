package com.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.StudentDTO;
import com.project.entity.Student;
import com.project.repository.StudentRepository;

import lombok.RequiredArgsConstructor;


//goes in component scaning and all maping are registered
@RestController
@RequiredArgsConstructor
public class StudentController {
	
	private final StudentRepository repo;
	
	@GetMapping("/student")
	public List<Student> getStudents() {
		//return new StudentDTO(1, "rishika", "rj@gmail"); //create using ctor
		//ned to convert student entity to student dto
		
		return repo.findAll();
	}
}
