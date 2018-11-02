package by.iba.student.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import common.Student;

public class StudentWriter {
	private final String path;
	public StudentWriter(String path){
		this.path=path;
		}
	public void write(List<Student> students) throws IOException{
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
			String format = "%s;%s;%s;%d;%d";
			for(Student student : students){
				bw.write(String.format(format,
						student.getId(),
						student.getFirstName(),
						student.getSecondName(),
						student.getGroupNumber(),
						student.getAvgMark()
						));
				bw.newLine();
			}
		}
	}
	
}
