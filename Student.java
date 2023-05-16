package streamspractice;

public class Student {
 //Parameters
	
	String name;
	String address;
	Long mobileNumber;
	String role;
    // Constructor, getters, and setters

	public Student() {
	}
	public Student(String name, String address, Long mobileNumber, String role) {
		super();
		this.name = name;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", address=" + address + ", mobileNumber=" + mobileNumber + ", role=" + role
				+ "]";
	}

}