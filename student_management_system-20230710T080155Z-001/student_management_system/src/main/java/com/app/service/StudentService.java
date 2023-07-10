package com.app.service;

import java.util.List;

import com.app.dto.StudentDto;

public interface StudentService {

	String addStudentDetails(StudentDto s);

	List<StudentDto> getAllStudentsByCourseTitle(String courseTitle);

	String deleteStudentById(Long studid);

	List<StudentDto> getAllStudents();

	String updateStudent(StudentDto updateStud);

	String updateStudentDetails(Long id, double score, String title);
}
