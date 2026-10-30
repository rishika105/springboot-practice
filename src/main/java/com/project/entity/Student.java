package com.project.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

//unique constraints define the constraints we put in db
//indexes are when we want to do faster lookup like in books we have index page and the marked chapter we can see pageno and go easily
@Entity
@Data
@Table(name = "student_tbl", uniqueConstraints = {
		@UniqueConstraint(name = "unique_student_email", columnNames = { "email" }) }, indexes = {
				@Index(name = "idx_student_name", columnList = "name") })
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer age;

	// db will be like this
	@Column(unique = true, nullable = false)
	private String email;

	// will not get updated on multiple sAVES
	// only first u get the created time
	@CreationTimestamp
	private LocalDateTime createdAt;

	// the blood group must come under the enum types only
	// it is checked in db otherwise throw an error
	@Enumerated(EnumType.STRING)
	private BloodGroupType bloodGroup;

}
