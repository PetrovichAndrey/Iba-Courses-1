package common;

import java.math.BigDecimal;

public class Professor {

	private String id;
	private String firstName;
	private String secondName;
	private BigDecimal avgMark;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public BigDecimal getAvgMark() {
		return avgMark;
	}
	public void setAvgMark(BigDecimal avgMark) {
		this.avgMark = avgMark;
	}
	@Override
	public String toString() {
		return "Professors [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", avgMark="
				+ avgMark + "]";
	}
	
	
	public Professor() {
		super();
	}
	
	public Professor(String id, String firstName, String secondName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avgMark == null) ? 0 : avgMark.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		if (avgMark == null) {
			if (other.avgMark != null)
				return false;
		} else if (!avgMark.equals(other.avgMark))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (secondName == null) {
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;
		return true;
	}
	
	
}
