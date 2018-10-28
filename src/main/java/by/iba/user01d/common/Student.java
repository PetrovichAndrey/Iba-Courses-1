package by.iba.user01d.common;

public class Student {
	private int id;
	private String firstName;
	private String secondName;
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	private int avgMark;
	private int groupNumber;
	
	public int getAvgMark() {
		return avgMark;
	}
	public void setAvgMark(int avgMark) {
		this.avgMark = avgMark;
	}
	public int getGroupNumber() {
		return groupNumber;
	}
	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String name) {
		this.firstName = name;
	}

	public int getAge() {
		return avgMark;
	}
	public void setAge(int age) {
		this.avgMark = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName
				+ ", secondName=" + secondName + ", avgMark=" + avgMark
				+ ", groupNumber=" + groupNumber + "]";
	}
	public Student(int id, String firstName, String secondName, int avgMark,
			int groupNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.avgMark = avgMark;
		this.groupNumber = groupNumber;
	}
	

}
