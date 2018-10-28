package by.iba.user01d.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.iba.user01d.common.*;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 6345194112526801506L;
	
	private final static List<Student> STUDENTS = new ArrayList<Student>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("students", STUDENTS);
		final RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!req.getParameter("action").equals("Add")){
			System.out.print("TEST");
			System.out.println(req.getParameter("action"));
			for (Iterator<Student> iter = STUDENTS.listIterator(); iter.hasNext(); ) {
			    Student s1 = iter.next();
			    if (s1.getId()==Integer.valueOf(req.getParameter("action"))) {
			        iter.remove();
			    }
			}
			doGet(req, resp);
		}
		else{
		final String idStr = req.getParameter("id");
		final String firstName = req.getParameter("firstName");
		final String avgMarkStr = req.getParameter("avgMark");
		final String secondName = req.getParameter("secondName");
		final String groupNumberStr = req.getParameter("groupNumber");
		final int id = Integer.valueOf(idStr);
		final int avgMark = Integer.valueOf(avgMarkStr);
		final int groupNumber = Integer.valueOf(groupNumberStr);
		//final String format = "firstName: %s, secondName: %s, avgMark: %d, groupNumber: %d";
		//System.out.print(String.format(format, firstName, secondName, avgMarkStr, groupNumberStr));
		STUDENTS.add(new Student(id, firstName, secondName, avgMark, groupNumber));
		doGet(req, resp);
		}
	}
}
