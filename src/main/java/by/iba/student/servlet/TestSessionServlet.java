package by.iba.student.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestSessionServlet extends HttpServlet {

	private static final long serialVersionUID = -196237532695912391L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String paramName = "test";
		String paramValue = (String) session.getAttribute(paramName);
		if (paramValue == null) {
			session.setAttribute("test", "test");
			System.out.println("SESSSSSIOONNNN");
		}	
		
	}
	
}
