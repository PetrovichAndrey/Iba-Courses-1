package by.iba.student.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.iba.student.dao.StudentDaoInMemory;
import by.iba.student.read.StudentReader;
import common.Student;

public class PageServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7027467148336530702L;
	private final ObjectMapper mapper = new ObjectMapper();
	private StudentDaoInMemory studentDao;

	private String path = "C:\\Users\\User-PC\\git\\ibaJavaProj\\src\\main\\resources\\students.csv";
	
	@Override
	public void init() throws ServletException{
		// TODO Auto-generated method stub
		try{
			List<Student> students = new StudentReader(path).read();
			studentDao = new StudentDaoInMemory(students);
		} catch(IOException e){
			
			throw new ServletException(e);
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//System.out.println(new PropertiesReader(getServletContext()).getProperty("datasource.name"));
		RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/jsp/students.jsp");
		rd.forward(req, resp);		
	}
	
	
	
}
