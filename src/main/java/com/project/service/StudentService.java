package com.project.service;

import java.util.List;
import java.util.Map;

import com.project.dto.StudentDTO;
import com.project.dto.StudentRequest;

public interface StudentService {
	List<StudentDTO> getAllStudents();

	StudentDTO getStudentById(Long pid);

	StudentDTO save(StudentRequest student);

	void delete(Long pid);

	StudentDTO updateStudent(Long pid, StudentRequest studentReq);

	StudentDTO updatePartialStudent(Long pid, Map<String, Object> updates);

}
