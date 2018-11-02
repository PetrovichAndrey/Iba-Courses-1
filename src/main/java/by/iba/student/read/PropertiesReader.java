package by.iba.student.read;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

public class PropertiesReader {
	
	private final static String PROPERTY_FILE_NAME = "application.properties";
	
	private static InputStream inputStream;
	
	private Properties prop;
	
	
	
	public PropertiesReader(ServletContext context) throws IOException {
		
		inputStream = context.getResourceAsStream("/resources/application.properties");
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + PROPERTY_FILE_NAME + "' not found in the classpath");
		}
	}
	
	public String getProperty(String nameProperty) throws IOException {
 
		try { 
			return prop.getProperty(nameProperty);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
			return null;
		} finally {
			inputStream.close();
		}
	}
}
