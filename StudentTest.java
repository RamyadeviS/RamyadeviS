package streamstest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import streamspractice.Student;

public class StudentTest {
	private static final boolean flag = false;

	public static void main(String[]args) {
		List<Student> studentList=new ArrayList<Student>();
	 
//adding student details
		studentList.add(new Student("Reema","204/78",8934561871l,"Designer"));
		studentList.add(new Student("Ramya","201/78",8934108729l,"Internship"));
		studentList.add(new Student("Surya","409/38",9820234560l,"Developer"));
		studentList.add(new Student("Sush","305/68",9940561873l,"Internship"));

//1.Get student with exact match name "Reema"

		List <String>studentNameList1= studentList.stream()
				.filter(student -> student.getName().equals("Reema"))//filtering data
				 .map(student -> student.getName()) //fetching name 
			    .collect(Collectors.toList()); //collecting as list 
		  System.out.println("Name:"+studentNameList1);
		
//2.Get student with matching address "201/78"
		   
	List<Student>studentNameList=studentList.stream()
			.filter(student -> student.getAddress().equals("201/78"))//filtering data
		    .collect(Collectors.toList()); //collecting as list 
	    System.out.println(studentNameList);
	   
//3.Get all student having mobile numbers 8934.
	
	 List<Student>studentMobileNumberList=studentList.stream()
				.filter(student -> student.getMobileNumber().toString()
						.startsWith("8934"))//filtering data
			    .collect(Collectors.toList()); //collecting as list 
		    System.out.println(studentMobileNumberList);
		   
//4.Get all student having mobile number 9820 and 9940
		    
		    List<Student>studentMobileNumberList1=studentList.stream()
					.filter(student -> student.getMobileNumber().toString()
					.startsWith("9820")||student.getMobileNumber().toString().startsWith("9940"))//filtering data
				    .collect(Collectors.toList()); //collecting as list 
			    System.out.println(studentMobileNumberList1);
			   
//6.Create a List<Student> from the List<TempStudent>	    
	List<String>tempStudentList=studentList.stream()
			.filter(temporary ->temporary.getRole().equals("Internship"))
			.map(temporary -> temporary.getName()) //fetching pages 
		    .collect(Collectors.toList()); //collecting as list 
	        System.out.println("Temporary Student List:"+tempStudentList);
	
//7.Convert List<Student> to List<String> of student name
	        List<String> studentNames = studentList.stream()
	                .map(Student::getName)
	                .toList();
	        System.out.println("Student Name List:"+studentNames);

//8.Convert List<students> to String
		String studentString=studentList.stream()
		.map(student -> "Name:"+student.getName()+", Address:"+ student.getAddress() +",Mobile Number:"+ student.getMobileNumber()+",Role: "+student.getRole())
		.collect(Collectors.joining(", "));// Concatenates the strings with a comma delimiter		   
        System.out.println(studentString);
  
//9.Change the case of List<String>
	List<String> studentList1=Arrays.asList("Reema","Ramya","Surya","Sush");

	List<String> upperCaseList = studentList1.stream()
          .map(String::toUpperCase)  // Convert each string to uppercase
          .collect(Collectors.toList());
       
          List<String> lowerCaseList = studentList1.stream()
          .map(String::toLowerCase)// Convert each string to Lowercase
          .collect(Collectors.toList());

        System.out.println("Uppercase strings: " + upperCaseList);
        System.out.println("Lowercase strings: " + lowerCaseList);

		/*
		 * List<String> upperCaseList=studentList.stream() .map(String::toUpperCase)//
		 * Convert each string to uppercase .collect(Collectors.toList());
		 */
        
//10.Sort List<String>
        List<String> sortedList = studentList1.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(sortedList);
//or
        List<String>sortedList1=studentList.stream()
        		.map(student -> "Name:"+student.getName()+", Address:"+ student.getAddress() +",Role: "+student.getRole())
    		    .sorted()
    			.collect(Collectors.toList()); //collecting as list 
    	    System.out.println(sortedList1);
    	    
 //11.Conditionally apply Filter condition, say if flag is enabled then.

	  List<Student> filteredList = studentList.stream() .filter(student -> flag ?
	  student.equals("Reema") : true) .collect(Collectors.toList());
	  System.out.println("Conditionally apply Filter condition, say if flag is enabled then:"+filteredList);
	
    	      	    
}
}

