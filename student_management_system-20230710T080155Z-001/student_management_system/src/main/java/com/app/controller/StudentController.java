package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.StudentDto;
import com.app.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping
	public ResponseEntity<?> addStudent(@RequestBody @Valid StudentDto s) {
		try {
			System.out.println("in add student "+s);
			return new ResponseEntity<>(new ApiResponse(studentService.addStudentDetails(s)), HttpStatus.CREATED);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new com.app.dto.ApiResponse(e.getMessage()));
		}
	}
	
	@GetMapping("/course_title/{courseTitle}")
	public ResponseEntity<?> getAllStudentsNyCourseTitle(@PathVariable String courseTitle) {
		try {
			System.out.println("in get students by course "+courseTitle);
			return new ResponseEntity<>(studentService.getAllStudentsByCourseTitle(courseTitle), HttpStatus.OK);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@DeleteMapping("/{studid}")
	public ApiResponse deleteStudentById(@PathVariable Long studid) {
		return new ApiResponse(studentService.deleteStudentById(studid));
	}
	
	@GetMapping
	public ResponseEntity<?> getAllStudents() {
		return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> updateStudent(@RequestBody StudentDto updateStud) {
		return new ResponseEntity<>(studentService.updateStudent(updateStud), HttpStatus.OK);
	}
	
	@PutMapping("/{id}/score/{scre}/course{course}/{title}")
	public ResponseEntity<?> updateStudentDetails(@PathVariable Long id, double score, String title) {
		return new ResponseEntity<>(studentService.updateStudentDetails(id, score, title), HttpStatus.OK);
	}
}
