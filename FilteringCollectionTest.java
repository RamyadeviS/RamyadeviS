package streamstest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import streamspractice.FilteringCollection;

public class FilteringCollectionTest {
 public static void main(String[]args) {
	List<FilteringCollection> bookList=new ArrayList<FilteringCollection>();
 
 //Adding Book Details
 bookList.add(new FilteringCollection("Agni Siragugal","A.P.J.Abdul Kalam",1000,"2014",800));
 bookList.add(new FilteringCollection("Unfinished","Priyanka Chopra Jonas",800,"2004",500));
 bookList.add(new FilteringCollection("Thirukkural","Thiruvalluvar",900,"2010",900));

 //1.Filtering Collection by using Stream
 
 List <Integer>bookPriceList=bookList.stream().filter(p -> p.getPrice()>800)//fitering data
		 .map(p -> p.getPrice()) //fetching price 
		 .collect(Collectors.toList()); //collecting as list 
 System.out.println(bookPriceList);
 
 List <String>bookNameList=bookList.stream().filter(p -> p.getBookName().length()<12)//fitering data
		 .map(p -> p.getBookName()) //fetching book name 
		 .collect(Collectors.toList()); //collecting as list 
 System.out.println(bookNameList);

 List <Integer>bookPageList=bookList.stream().filter(p -> p.getPages()>500)//fitering data
		 .map(p -> p.getPages()) //fetching pages 
		 .collect(Collectors.toList()); //collecting as list 
 System.out.println(bookPageList);

 //2.Filtering and Iterating Collection
 
 // This is more compact approach for filtering data  
 bookList.stream().filter(book -> book.getPrice()==800)
 .forEach(book -> System.out.println(book.getBookName()));    

 bookList.stream().filter(book -> book.getPages()==500)
 .forEach(book -> System.out.println(book.getAuthorName()));    

  bookList.stream().filter(book -> book.getAuthorName()=="A.P.J.Abdul Kalam")
  .forEach(book -> System.out.println(book.getBookName()));
 
  //3.reduce() Method in Collection
  
  // This is more compact approach for filtering data  
  int totalPrice=bookList.stream()
		.map(total ->total.getPrice())
		.reduce((int) 0.0f,(sum, price)->sum+price);// accumulating price
	        System.out.println(totalPrice);
	        
   int totalPrice1=bookList.stream()
	     .map(total ->total.getPrice())
	     .reduce((int) 2000,(sum, price)->sum+price);// accumulating price
	     System.out.println(totalPrice1);
	
  // More precise code   
   int totalPrice2=bookList.stream()
	 .map(total ->total.getPrice())
	 .reduce((int) 1.0f,Integer::sum);  // accumulating price, by referring method of Float class  
   System.out.println(totalPrice2);	
   
   
 }
}