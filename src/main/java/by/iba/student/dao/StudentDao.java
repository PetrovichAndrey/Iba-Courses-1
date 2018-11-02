package by.iba.student.dao;

import java.util.List;

import common.Student;

public interface StudentDao {
	
	public List<Student> findAll();
	
	public Student findOne(String id);
	
	public Student create(Student student);
	
	public Student update(Student student);
	
	public void remove(String id);
	
}
