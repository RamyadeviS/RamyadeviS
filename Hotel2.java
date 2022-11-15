package com.chainsys.day2;
import java.util.Scanner;
public class Hotel2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Scanner sc=new Scanner(System.in);
Hotel hot=new Hotel();
System.out.println("Enter hotel name");
String hotelName=sc.next();
System.out.println("enter special");
String special=sc.next();
System.out.println("enter seats");
int seats=sc.nextInt();
System.out.println("enter phone number");
long phoneNumber=sc.nextLong();
System.out.println("enter mail id");
String mailId=sc.next();
System.out.println(hotelName);
System.out.println(special);
System.out.println(seats);
System.out.println(phoneNumber);
System.out.println(mailId);


	}

}
