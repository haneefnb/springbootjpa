package com.tipsol.springbootjpa.school;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	@Autowired
	StudentRepo repo;
	
	@Value("${spring.datasource.username}")
	String dbUserName;
	
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<Student>();
		Iterator<Student> studItr =repo.findAll().iterator(); 
		while (studItr.hasNext()) {
			students.add(studItr.next());
		}
		System.out.println("=============DB User========="+dbUserName);
		return students;
	}
	
	public Student createStudent(Student stud) {
		Student s = repo.save(stud);
		return s;
	}

	public Student getStudent(Long id) {
		return repo.findById(id).get();
	}
	
	
}
