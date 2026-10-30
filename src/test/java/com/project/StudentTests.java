package com.project;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.entity.Student;
import com.project.repository.StudentRepository;

@SpringBootTest
public class StudentTests {

	@Autowired
	private StudentRepository studentRepo;

	// without running the sprinboot application you can test this queries
	// here we print list of students
	@Test
	public void testStudentRepo() {

		List<Student> studentList = studentRepo.findAll();
		System.out.println(studentList);
	}

}
