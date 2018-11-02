package by.iba.student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import by.iba.student.util.StringUtil;
import common.Student;

public class StudentDaoDb implements StudentDao {
	
	private DataSource dataSource;
	
	public StudentDaoDb(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private static String buildSql() {
		return 
			"SELECT "
				+ "ST.STUDENT_ID, "
				+ "ST.FIRST_NAME, "
				+ "ST.SECOND_NAME, "
				+ "ST.AVG_MARK, "
				+ "ST.GROUP_NUMBER "
			+ "FROM BEGANSS.STUDENT ST ";
	}
	
	@Override
	public List<Student> findAll() {
		String sql = buildSql();
		List<Student> students = new ArrayList<Student>();		
		try (Connection connection = dataSource.getConnection()){	
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Student st = new Student();
				st.setId(StringUtil.trim(rs.getString("STUDENT_ID")));
				st.setFirstName(rs.getString("FIRST_NAME"));
				st.setSecondName(rs.getString("SECOND_NAME"));
				st.setAvgMark(rs.getBigDecimal("AVG_MARK"));
				st.setGroupNumber(StringUtil.trim(rs.getString("GROUP_NUMBER")));
				students.add(st);
			}
		} catch (Throwable ex) {
			throw new RuntimeException(ex);
		}
				
		return students;
	}

	@Override
	public Student findOne(String id) {
		String sql = 
				buildSql() + 
				"WHERE ST.STUDENT_ID = ?";
		
		List<Student> students = new ArrayList<Student>();		
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Student st = new Student();
				st.setId(rs.getString("STUDENT_ID"));
				st.setFirstName(rs.getString("FIRST_NAME"));
				st.setSecondName(rs.getString("SECOND_NAME"));
				st.setAvgMark(rs.getBigDecimal("AVG_MARK"));
				st.setGroupNumber(rs.getString("GROUP_NUMBER").trim());
				students.add(st);
			}
		} catch (Throwable ex) {
			throw new RuntimeException(ex);
		}
		
		if (students.size() == 1) {
			return students.get(0);
		}
		throw new RuntimeException("Student not found");
	}

	@Override
	public Student create(Student student) {
		String sql = 
				"INSERT INTO BEGANSS.STUDENT("						
					+ "FIRST_NAME, " //1
					+ "SECOND_NAME, " //2
					+ "GROUP_NUMBER "  //3
				+ ") VALUES ("
					+ "?, " //1
					+ "?, " //2
					+ "? " //3
				+")";
		
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, student.getFirstName());
			ps.setString(2, student.getSecondName());
			ps.setString(3, String.valueOf(student.getGroupNumber()));
			ps.executeUpdate();
			
			String id = null;
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {
				id = rs.getString("STUDENT_ID");
			}
			if (id == null) {
				throw new SQLException("Id was not generated");
			}
			student.setId(id);
		} catch (Throwable ex) {
			throw new RuntimeException(ex);
		}
		return student;
	}

	@Override
	public Student update(Student student) {
		String sql =
				"UPDATE BEGANSS.STUDENT ST "
					+ "SET FIRST_NAME = ?, "//1
					+ "SECOND_NAME = ?, "	//2
					+ "AVG_MARK = ?, "		//3
					+ "GROUP_NUMBER = ? "	//4
				+ "WHERE "
					+ "ST.STUDENT_ID = ? "; //5
		
		try(Connection connection = dataSource.getConnection()){
			PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, student.getFirstName());
			ps.setString(2, student.getSecondName());
			ps.setBigDecimal(3, student.getAvgMark());
			ps.setString(4, student.getGroupNumber());
			ps.setString(5, student.getId());
			ps.executeUpdate();
			
			return student;
		}catch (SQLException e) {
			//
			return null;
		}
		
		
	}

	@Override
	public void remove(String id) {		
		String sql =
				"DELETE FROM BEGANSS.STUDENT ST "
				+ "WHERE ST.STUDENT_ID = ? ";
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, id);			
			ps.executeUpdate();			
		} catch (Throwable ex) {
			throw new RuntimeException(ex);
		}
		
	}

}
