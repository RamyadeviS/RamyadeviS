package streamstest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import streamspractice.Employee;

public class EmployeeTest {

	public static void main(String[]args){
  List<Employee> employeeList=new ArrayList<Employee>();
  
  //Adding Employee details
  
  employeeList.add(new Employee("Vijay","Manager",76000));
  employeeList.add(new Employee("Raja","Developer",70000));
  employeeList.add(new Employee("Jaya","HR",55000));
  employeeList.add(new Employee("Krishna","Designer",65000));
  employeeList.add(new Employee("Selvarasu","",60000));
  
//5.Group an array of employee records into a data map organized by job title
    
  List<String>employeeJobTitle=employeeList.stream().filter(employee -> employee.getSalary()<78000)//fitering data
			 .map(p -> p.getJobTitle()) //fetching price 
			 .collect(Collectors.toList()); //collecting as list 
	 System.out.println(employeeJobTitle);
  
	 //or 
	 
	List<String>employeeJobTitle1=employeeList.stream()
			.map(Employee::getJobTitle).toList();
    System.out.println("Employee Job Title:"+employeeJobTitle1);

	/*
	 * Map<String,List<Employee>> employeeJobTitle=employeeList.stream()
	 * .collect(Collectors.groupingBy(Employee::getJobTitle));
	 * System.out.println("Job:"+employeeJobTitle);
	 */
    
 //12.Calculate average salary of all employee in the list.

        double averageSalary = employeeList.stream()
            .mapToDouble(Employee::getSalary)
            .average()
            .orElse(0.0);
 System.out.println("Average Salary: " + averageSalary);
	}
}


