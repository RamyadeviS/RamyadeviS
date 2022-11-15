package com.chainsys.day2;

import java.util.Scanner;

public class Book2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Scanner sc=new Scanner(System.in);
Book book=new Book();
System.out.println("Enter book Name");
String bookName=sc.next();
System.out.println("Enter author");
String author=sc.next();
System.out.println("Enter cost");
int cost=sc.nextInt();
System.out.println("Enter pages");
int pages=sc.nextInt();
book.bookName=bookName;
book.author=author;
book.cost=cost;
book.pages=pages;
System.out.println(book.bookName);
System.out.println(book.author);
System.out.println(book.cost);
System.out.println(book.pages);


	}

}
