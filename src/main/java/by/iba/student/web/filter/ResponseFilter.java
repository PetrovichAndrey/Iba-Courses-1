package by.iba.student.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.iba.student.servlet.ServletConstants;

public class ResponseFilter implements Filter {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
				
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(request, response);
		Object responseObject = request.getAttribute(ServletConstants.RESPONSE_PARAM_NAME);
		if (responseObject != null) {
			response.setContentType("application/json");
			
			PrintWriter pw = response.getWriter();			
			pw.write(mapper.writeValueAsString(responseObject));
			pw.close();
		}
	}

	@Override
	public void destroy() {
				
	}

}
