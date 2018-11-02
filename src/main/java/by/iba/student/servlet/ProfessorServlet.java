package by.iba.student.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.iba.student.dao.ProfessorDao;

public class ProfessorServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -354349082074497154L;
	private ProfessorDao professorDao;
	private ObjectMapper mapper = new ObjectMapper();
	
	public ProfessorServlet() {
		
	}

	public ProfessorServlet(ProfessorDao professorDao, ObjectMapper mapper) {
		this.professorDao = professorDao;
		this.mapper = mapper;
	}
	
	@Override
	public void init() throws ServletException{		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//String path = req.getPathInfo();
		//String name = req.getParameter("name");
		//if(name == null)
			//if(path == null) {
				req.setAttribute(ServletConstants.RESPONSE_PARAM_NAME, professorDao.findAll());
			}/*else {
				String[] data = path.split("/");
				String id = data[1];
				req.setAttribute(ServletConstants.RESPONSE_PARAM_NAME, studentDao.findOne(id));
			}*/
		//else {
		//				
		//}
	//}
	
	@Override
	public void destroy() {		
		
	}
	
	

}
