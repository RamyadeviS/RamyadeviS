package streamspractice;

public class Employee {

	String name;
	String jobTitle;
	double salary;
	
	public Employee() {
	}

	public Employee(String name, String jobTitle, double salary) {
		super();
		this.name = name;
		this.jobTitle = jobTitle;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", jobTitle=" + jobTitle + ", salary=" + salary + "]";
	}
	
	
}
