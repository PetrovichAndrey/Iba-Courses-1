package by.iba.student.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.Student;
import common.User;

public class UserReader {

	private final String path;
	public UserReader(String path){
		this.path=path;
	}
	
	public List<User> read() throws IOException{
		List<User> users = new ArrayList<>();
		try(BufferedReader br=new BufferedReader(new FileReader(path))){
			String line = null;
			while((line=br.readLine())!=null){
				String[] data = line.split(";");
				User user=new User();
				user.setName(data[0]);
				user.setPassword(data[1]);
				users.add(user);
				
			}
		}
		return users;		
	}
	
}
