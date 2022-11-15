package com.chainsys.day2;
import java.util.Scanner;
public class Vehicle2 {

	public static void main(String[] args) {
		System.out.println();
Vehicle1 vec=new Vehicle1();
Scanner sc=new Scanner(System.in);
System.out.println();
System.out.println("enter vehicle name");
String vehicleName=sc.next();
System.out.println("enter price");
int price=sc.nextInt();
System.out.println("enter colour");
String colour=sc.next();
System.out.println("enter register number");
long registerNumber=sc.nextLong();
System.out.println("available blue car");
Boolean availableBlueCar=sc.nextBoolean();
System.out.println(vehicleName);
System.out.println(price);
System.out.println(colour);
System.out.println(registerNumber);
System.out.println(availableBlueCar);


	}

}
