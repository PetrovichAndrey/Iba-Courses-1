package by.iba.student.web;

import java.util.HashMap;
import java.util.Map;

public class Response {
	
	private Map<String, Object> arguments = new HashMap<>();
	
	public Response(Map<String, Object> arguments) {
		this.arguments = arguments;
	}
	
	public Response addArgument(String name, Object argument) {
		this.arguments.put(name, argument);
		return this;
	}
	
	public Map<String, Object> getArguments() {
		return arguments;
	}

}
