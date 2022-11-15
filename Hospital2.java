package com.chainsys.day2;
import java.util.Scanner;
public class Hospital2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Hospital hospital=new Hospital();
Scanner sc=new Scanner(System.in);
System.out.println("Enter hospital name");
String hospitalName=sc.next();
System.out.println("Enter place");
String place=sc.next();
System.out.println("Enter hospital beds");
int hospitalBeds=sc.nextInt();
System.out.println("Enter phone number");
long phoneNumber=sc.nextLong();
System.out.println(hospitalName);
System.out.println(place);
System.out.println(hospitalBeds);
System.out.println(phoneNumber);
	}
}
