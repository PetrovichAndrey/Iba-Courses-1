package by.iba.student.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.iba.student.read.UserReader;
import common.User;

public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = -3857005494044261293L;
	private static final String path = "C:\\Users\\User-PC\\git\\ibaJavaProj\\src\\main\\resources\\users.csv";
	
	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> userList = new UserReader(path).read();
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		
		for (User u : userList) {
			if(u.getName().equals(user.getName()) && u.getPassword().equals(user.getPassword())) {
				HttpSession session = req.getSession();
				session.setAttribute(ServletConstants.USER_PARAM_NAME, user.getName());
				resp.sendRedirect(req.getContextPath() + "/page");
				return;
			}
		}
		
		req.setAttribute("error", "Invalid login or password!!!");
		doGet(req, resp);
	}
	
}
