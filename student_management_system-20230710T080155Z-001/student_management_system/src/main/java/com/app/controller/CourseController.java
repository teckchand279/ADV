package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.CreateNewCourse;
import com.app.service.CourseServive;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseServive courseService;
	public CourseController() {
		System.out.println("on course controller");
	}
	@PostMapping
	public ResponseEntity<?> launchCourse(@RequestBody @Valid CreateNewCourse course) {
		try {
			System.out.println("in launch course " + course);
			return new ResponseEntity<>(courseService.launchNewCourses(course), HttpStatus.CREATED);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}/fees/{fee}")
	public ResponseEntity<?> updateCourseFees(@PathVariable Long id, @PathVariable double fee) {
		try {
			return new ResponseEntity<>(courseService.updateCourseFees(id, fee), HttpStatus.OK);
		}catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@GetMapping("/courses")
	public ResponseEntity<?> getAllCourses() {
		return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
	}
}
