package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.StudentDto;
import com.app.entities.Course;
import com.app.entities.Student;
import com.app.repository.CourseRepository;
import com.app.repository.StudentRepository;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private CourseRepository courseRepo;

	@Override
	public String addStudentDetails(StudentDto s) {
		Course course = courseRepo.findByTitle(s.getCourseTitle())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid course title!!!!"));
		if (s.getScore() > course.getMinScare()) {
			Student student = mapper.map(s, Student.class);
			course.addStudent(student);
			Student persistentStudent = studentRepo.save(student);
			return "Student admitted";
		}
		return "Student can't be admitted, Less Score!!!";
	}

	@Override
	public List<StudentDto> getAllStudentsByCourseTitle(String courseTitle) {
		List<Student> students = studentRepo.findAllByEndrolledCourseTitle(courseTitle)
				.orElseThrow(() -> new ResourceNotFoundException("no students endrolled in course "+courseTitle));
		
		return students.stream().map(student -> mapper.map(student, StudentDto.class)).collect(Collectors.toList());
	}

	@Override
	public String deleteStudentById(Long studid) {
		if(studentRepo.existsById(studid))
		{
			studentRepo.deleteById(studid);
			return "Student deleted of id : "+studid;
		}
		return "Student not deleted!!!!!";
	}

	@Override
	public List<StudentDto> getAllStudents() {
		List<Student> students = studentRepo.findAll();
		return students.stream().map(s -> mapper.map(s, StudentDto.class)).collect(Collectors.toList());
	}

	@Override
	public String updateStudent(StudentDto updateStud) {
		if(studentRepo.existsById(updateStud.getId())) {
			Student student = studentRepo.save(mapper.map(updateStud, Student.class));
			return "Updated Student " +student.getFirstName();
		}
		return "Student not found";
	}

	@Override
	public String updateStudentDetails(Long id, double score, String title) {
		Student student = studentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found"));
		student.setScore(score);
		Course course = courseRepo.findByTitle(title).orElseThrow(() -> new ResourceNotFoundException("title not found"));
		student.setEndrolledCourse(course);
		return "Student details updated";
	}

}
