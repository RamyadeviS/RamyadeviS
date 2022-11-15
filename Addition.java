package com.chainsys.day2;

import java.util.Scanner;

public class Addition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Addition addition=new Addition();
Scanner sc=new Scanner(System.in);
System.out.println("Enter a number");
int number1=sc.nextInt();
System.out.println("Enter an another number");
int number2=sc.nextInt();
System.out.println("Addition of given numbers:"+(number1+number2));
System.out.println("Multiplication of given numbers:"+(number1*number2));
System.out.println("Division of given numbers:"+(number1/number2));


	}

}
