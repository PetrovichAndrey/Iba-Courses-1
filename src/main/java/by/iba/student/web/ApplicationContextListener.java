package by.iba.student.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.iba.student.dao.ProfessorDao;
import by.iba.student.dao.ProfessorDaoDb;
import by.iba.student.dao.StudentDao;
import by.iba.student.dao.StudentDaoDb;
import by.iba.student.dao.StudentDaoInMemory;
import by.iba.student.servlet.LoginServlet;
import by.iba.student.servlet.ProfessorServlet;
import by.iba.student.servlet.StudentServlet;
import by.iba.student.servlet.TestSessionServlet;
import by.iba.student.web.listener.LoggerSessionListener;
import by.iba.student.write.StudentWriter;
import common.Student;

public class ApplicationContextListener implements ServletContextListener{
	
	private final Map<String, Object> context = new HashMap<>();
	
	private final static String MAPPER_NAME = "mapper";
	
	private final static String STUDENT_DAO_NAME = "studentDao";
	
	private final static String PROFESSOR_DAO_NAME = "professorDao";
	
	private final static String STUDENT_FILE_PATH_PARAM_NAME = "students.file.path";
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		initContext(sc);
		
		sc.addServlet("StudentServlet", new StudentServlet(
					getContextParam(STUDENT_DAO_NAME, StudentDao.class),
					getContextParam(MAPPER_NAME, ObjectMapper.class)
				))
			.addMapping("/students/*");
		
		sc.addServlet("SessionServlet", new TestSessionServlet())
			.addMapping("/session");
		
		sc.addServlet("LoginServlet", new LoginServlet())
			.addMapping("/login");
		
		sc.addServlet("ProfessorServlet", new ProfessorServlet(
				getContextParam(PROFESSOR_DAO_NAME, ProfessorDao.class),
				getContextParam(MAPPER_NAME, ObjectMapper.class)
				))
			.addMapping("/professor");
		
		sc.addListener(new LoggerSessionListener());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
	
	protected void initContext(ServletContext sc) {
		context.put(MAPPER_NAME, initMapper());
		context.put(STUDENT_DAO_NAME, studentDaoDb());	
		context.put(PROFESSOR_DAO_NAME, professorDaoDb());
	}
	
	@SuppressWarnings("unchecked")
	protected<T> T getContextParam(String name, Class<T> clazz) {
		return (T) this.context.get(name);
	}
	
	private ObjectMapper initMapper() {
		return new ObjectMapper();
	}
	/*
	private StudentDaoInMemory studentDaoInMemory(ServletContext sc) {
		try{
			List<Student> students = new StudentReader(sc.getInitParameter(STUDENT_FILE_PATH_PARAM_NAME)).read();
			return new StudentDaoInMemory(students);
		} catch(IOException e){			
			throw new RuntimeException(e);
		}
	}
	*/
	private StudentDaoDb studentDaoDb() {
		return new StudentDaoDb(dataSource());
	}
	
	private ProfessorDaoDb professorDaoDb() {
		return new ProfessorDaoDb(dataSource());
	}
	
	private void saveStudents(ServletContext sc) {
		try {
			List<Student> students = getContextParam(STUDENT_DAO_NAME, StudentDaoInMemory.class).findAll();
			new StudentWriter(sc.getInitParameter(STUDENT_FILE_PATH_PARAM_NAME)).write(students);
		} catch (IOException e) {
			System.out.println("Could not save students");
		}
	}
	
	private DataSource dataSource() {
		InitialContext context;
		try {
			context = new InitialContext();
			return (DataSource) context.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	

}
