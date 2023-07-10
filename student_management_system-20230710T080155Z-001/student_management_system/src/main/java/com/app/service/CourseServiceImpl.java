package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.CreateNewCourse;
import com.app.entities.Course;
import com.app.repository.CourseRepository;

@Service
@Transactional
public class CourseServiceImpl implements CourseServive {

	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public String launchNewCourses(CreateNewCourse course) {
		Course persistentCourse = courseRepo.save(mapper.map(course, Course.class));
		return persistentCourse.getTitle() + " course added....";
	}

	@Override
	public String updateCourseFees(Long id, double fees) {
		Course course = courseRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid course id!!!!!"));
		course.setFees(fees);
		return "Updated course fees of " +course.getTitle() + " to new fees : " + fees ;
	}

	@Override
	public List<CreateNewCourse> getAllCourses() {
		List<Course> courses = courseRepo.findAll();
		return courses.stream().map(c -> mapper.map(c, CreateNewCourse.class)).collect(Collectors.toList());
	}

}
