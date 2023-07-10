package com.app.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "courses")
@ToString

public class Course extends BaseEntity {
	
	@Column(unique = true, length = 20)
	private String title;
	private LocalDate startDate;	
	private LocalDate endDate;	
	private double fees;
	private double minScare;
	
	@OneToMany(mappedBy = "endrolledCourse", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Student> students = new ArrayList<Student>();

	public Course(String title, LocalDate startDate, LocalDate endDate, double fees, double minScare) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.fees = fees;
		this.minScare = minScare;
	}
	
	public void addStudent(Student student) {
		students.add(student);
		student.setEndrolledCourse(this);
	}
	
	public void removeStudent(Student student) {
		students.remove(student);
		student.setEndrolledCourse(null);
	}
	
}
