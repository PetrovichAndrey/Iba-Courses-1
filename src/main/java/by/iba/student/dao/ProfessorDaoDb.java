package by.iba.student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import by.iba.student.util.StringUtil;
import common.Professor;

public class ProfessorDaoDb implements ProfessorDao{

	private DataSource dataSource;

	public ProfessorDaoDb(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private static String buildSql() {
		return 
			"SELECT "
				+ "PROF.PROFESS_ID, "
				+ "PROF.FIRST_NAME, "
				+ "PROF.SECOND_NAME, "
				+ "PROF.AVG_MARK "
			+ "FROM BEGANSS.PROFESS PROF ";
	}
	
	@Override
	public List<Professor> findAll() {
		String sql = buildSql();
		List<Professor> professors = new ArrayList<Professor>();		
		try (Connection connection = dataSource.getConnection()){	
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Professor prof = new Professor();
				prof.setId(StringUtil.trim(rs.getString("PROFESS_ID")));
				prof.setFirstName(StringUtil.trim(rs.getString("FIRST_NAME")));
				prof.setSecondName(StringUtil.trim(rs.getString("SECOND_NAME")));
				prof.setAvgMark(rs.getBigDecimal("AVG_MARK"));
				professors.add(prof);
			}
		} catch (Throwable ex) {
			throw new RuntimeException(ex);
		}
				
		return professors;
	}
	
}
