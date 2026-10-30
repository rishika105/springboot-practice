package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.entity.BloodGroupType;
import com.project.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	// jpa derived query method
	List<Student> findByNameAndAgeGreaterThan(String name, Integer age);

	// jpql
	// @Query("select p from patient p where p.bloodGroup = ?1")
	// use direct the param name instead of =?1 coz it might do sql injecction
	// attack!
	// JPQL entity names are case-sensitive use Student and not student
	// it used enetity names only
	// where blooodgrp eql to the suppkied bloodgroup
	@Query("select s from Student s where s.bloodGroup = :bloodGroup")
	List<Student> findByBloodGroup(BloodGroupType bloodGroup);

	// note: student_tbl is db level here entity is student
	// select blood_group, count(*)
	// from student_tbl
	// where blood_group is not null
	// group by blood_group
	@Query("select s.bloodGroup, count(s) from Student s where s.bloodGroup is not null group by s.bloodGroup")
	List<Object[]> countEachBloodGroupType();

}
