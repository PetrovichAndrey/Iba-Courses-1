package by.iba.student.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.iba.student.dao.StudentDao;
import common.Student;

public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 6345194112526801506L;
	
	private StudentDao studentDao;
	private ObjectMapper mapper = new ObjectMapper();	
	
	public StudentServlet() {
		
	}
	
	public StudentServlet(StudentDao studentDao, ObjectMapper mapper) {
		this.studentDao = studentDao;
		this.mapper = mapper;
	}
	
	@Override
	public void init() throws ServletException{		
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = req.getPathInfo();
		String name = req.getParameter("name");
		if(name == null)
			if(path == null) {
				req.setAttribute(ServletConstants.RESPONSE_PARAM_NAME, studentDao.findAll());
			}else {
				String[] data = path.split("/");
				String id = data[1];
				req.setAttribute(ServletConstants.RESPONSE_PARAM_NAME, studentDao.findOne(id));
			}
		else {
						
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Student student = mapper.readValue(req.getInputStream(), Student.class);
		student = studentDao.create(student);	
		req.setAttribute(ServletConstants.RESPONSE_PARAM_NAME, student);
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getPathInfo();
		String[] data = path.split("/");
		String id = data[1];
		studentDao.remove(id);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Student student = mapper.readValue(req.getInputStream(), Student.class);
		student = studentDao.update(student);
		req.setAttribute(ServletConstants.RESPONSE_PARAM_NAME, student);
	}
	
	@Override
	public void destroy() {		
		
	}
}
