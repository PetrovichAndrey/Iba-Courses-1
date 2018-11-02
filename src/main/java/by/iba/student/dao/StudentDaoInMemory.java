package by.iba.student.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import common.Student;

public class StudentDaoInMemory implements StudentDao {
	private final Map<String,Student> students=new LinkedHashMap<>();
	public StudentDaoInMemory(List<Student> students){
		if(students!=null){
			for(Student student : students){
				this.students.put(student.getId(), student);
			}
		}
	}
	
	@Override
	public List<Student> findAll(){
		return new ArrayList<Student>(students.values());
	}
	
	@Override
	public Student findOne(String id) {
		return students.get(id);
	}
	
	public List<Student> findByName(String name) {
		
		if(name.equals(""))
			return findAll();
		List<Student> studentsList = new ArrayList<>();
		for (Student student : students.values()) {
			if(student.getFirstName().equals(name)) {
				studentsList.add(student);
			}
		}
		return studentsList;
	}
	
	@Override
	public Student create(Student student){
		String id = UUID.randomUUID().toString();
		student.setId(id);
		students.put(id, student);
		return student;
	}
	
	@Override
	public Student update(Student student) {
		String id = student.getId();
		students.replace(id, students.get(id) , student);
		return student;
	}
	
	@Override
	public void remove(String id){
		students.remove(id);
	}
}
