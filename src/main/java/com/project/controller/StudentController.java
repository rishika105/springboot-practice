package com.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.StudentDTO;
import com.project.dto.StudentRequest;
import com.project.repository.StudentRepository;
import com.project.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//goes in component scaning and all maping are registered
@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

	private final StudentService service;
	private final StudentRepository repo;

	@GetMapping
	public ResponseEntity<List<StudentDTO>> getStudents() {
		// return new StudentDTO(1, "rishika", "rj@gmail"); //create using ctor
		// ned to convert student entity to student dto

		return ResponseEntity.ok(service.getAllStudents());
	}

	@GetMapping("/{pid}")
	// the above id name must same as the below path var
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long pid) {
		return ResponseEntity.ok(service.getStudentById(pid));
	}

	@PostMapping
	public ResponseEntity<StudentDTO> saveStudent(@Valid @RequestBody StudentRequest student) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(student));
	}

	@DeleteMapping("/{pid}")
	public ResponseEntity<?> deleteStudent(@PathVariable Long pid) {
		service.delete(pid);
		return ResponseEntity.noContent().build();
	}

	// change the whole student entity
	// while in patch do partial change
	@PutMapping("/{pid}")
	public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long pid,
			@Valid @RequestBody StudentRequest student) {
		return ResponseEntity.ok(service.updateStudent(pid, student));
	}

	@PatchMapping("/{pid}")
	public ResponseEntity<StudentDTO> updatePartialStudent(@PathVariable Long pid,
			@RequestBody Map<String, Object> updates) {
		return ResponseEntity.ok(service.updatePartialStudent(pid, updates));

	}

	@GetMapping("/testingJpa")
	// just call jpa method here and return entity only for testing
//	public ResponseEntity<List<Student>> testJpaMethods(@RequestParam String name, @RequestParam Integer age) {
//		return ResponseEntity.ok(repo.findByNameAndAgeGreaterThan(name, age));
//
//	}

//	public ResponseEntity<List<Student>> testJpaMethods(@RequestParam BloodGroupType bloodGroup) {
//		return ResponseEntity.ok(repo.findByBloodGroup(bloodGroup));
//
//	}

	public ResponseEntity<?> testJpaMethods() {
		// return ResponseEntity.ok(repo.countEachBloodGroupType());
//		return ResponseEntity.ok(repo.findAllStudents());
		// gets 2 adata at a time
		// here we get only 0th page data as we have metntioned explicity
		// means first 2 data in this case
		return ResponseEntity.ok(repo.findAllStudents(PageRequest.of(0, 2, Sort.by("name"))));

	}

//	public ResponseEntity<?> testJpaMethods(@RequestParam Long id, @RequestParam String name) {
//		return ResponseEntity.ok(repo.updateNameWithId(name, id));
//	}

}
