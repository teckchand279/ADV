package com.app.service;

import java.util.List;

import com.app.dto.CreateNewCourse;
import com.app.entities.Course;

public interface CourseServive {
	
	String launchNewCourses(CreateNewCourse c);
	
	String updateCourseFees(Long id, double fee);

	List<CreateNewCourse> getAllCourses();

}
