package by.iba.student.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.iba.student.servlet.ServletConstants;
import common.User;

public class AuthFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException { 
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String url = req.getRequestURI();
		
		
		HttpSession session = req.getSession();
		if (session.getAttribute(ServletConstants.USER_PARAM_NAME) != null) {
			chain.doFilter(request, response);
		} else {
			if (url.endsWith("/login")) {
				chain.doFilter(request, response);
			}else {
				resp.sendRedirect(req.getContextPath() + "/login");
			}
		}
	}

	@Override
	public void destroy() {

	}

}
