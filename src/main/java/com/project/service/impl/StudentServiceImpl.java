package com.project.service.impl;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.dto.StudentDTO;
import com.project.dto.StudentRequest;
import com.project.entity.Student;
import com.project.repository.StudentRepository;
import com.project.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepository repo;
	private final ModelMapper modelMapper;

	@Override
	public List<StudentDTO> getAllStudents() {
		List<Student> students = repo.findAll();

		// convert entity to dto
		List<StudentDTO> studentDtoList = students.stream().map(student -> modelMapper.map(student, StudentDTO.class))
				.toList();

		return studentDtoList;
	}

	@Override
	public StudentDTO getStudentById(Long pid) {
		// TODO Auto-generated method stub
		// if returns optional need to use or else throw
		Student student = repo.findById(pid)
				.orElseThrow(() -> new IllegalArgumentException("cannot find student with this id"));
		// map(source, destination)
		// both eneity and dto mist have same field name for it to convert
		return modelMapper.map(student, StudentDTO.class);
	}

	@Override
	public StudentDTO save(StudentRequest student) {
		Student newStudent = modelMapper.map(student, Student.class); // dto to entity
		Student stud = repo.save(newStudent); // save in db

		return modelMapper.map(stud, StudentDTO.class); // entity to dto and return
	}

	@Override
	public void delete(Long pid) {
		if (!repo.existsById(pid)) {
			throw new IllegalArgumentException("student does not exist by id" + pid);
		}

		repo.deleteById(pid);
	}

	@Override
	public StudentDTO updateStudent(Long pid, StudentRequest studentReq) {
		Student student = repo.findById(pid)
				.orElseThrow(() -> new IllegalArgumentException("cannot find student with this id"));
		modelMapper.map(studentReq, student); // updates in the entity object

		// now student is updated
		// save in db
		// save operation identifies itself if we need to createa new one or update
		// existing
		student = repo.save(student);
		return modelMapper.map(student, StudentDTO.class);
	}

	@Override
	public StudentDTO updatePartialStudent(Long pid, Map<String, Object> updates) {
		Student student = repo.findById(pid)
				.orElseThrow(() -> new IllegalArgumentException("cannot find student with this id"));

		// use key value and only update the required field
		updates.forEach((field, value) -> {
			switch (field) {
			case "name":
				student.setName((String) value);
				break;
			case "email":
				student.setEmail((String) value);
				break;
			default:
				throw new IllegalArgumentException("Field is not supported");
			}

		});
		Student updatedStudent = repo.save(student);
		return modelMapper.map(updatedStudent, StudentDTO.class);
	}

}
