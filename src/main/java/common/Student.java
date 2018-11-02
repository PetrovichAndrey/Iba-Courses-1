package common;

import java.math.BigDecimal;

public class Student {
	private String firstName;
	private String secondName;
	private String groupNumber;
	private BigDecimal avgMark;
	private String id;
	public Student() {
		super();
	}
	public Student(String firstName, String secondName, String groupNumber, BigDecimal avgMark) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.groupNumber = groupNumber;
		this.avgMark = avgMark;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getGroupNumber() {
		return groupNumber;
	}
	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}
	public BigDecimal getAvgMark() {
		return avgMark;
	}
	public void setAvgMark(BigDecimal avgMark) {
		this.avgMark = avgMark;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
