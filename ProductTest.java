package streamstest;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import streamspractice.Customer;
import streamspractice.Employee;
import streamspractice.Order;
import streamspractice.Product;
import streamspractice.Student;

public class ProductTest {

	public static void main(String[]args) {
	Customer customer=new Customer();
		List <Customer>customerList=new ArrayList<Customer>();
//Adding Customer Details
		customerList.add(new Customer(3330l,"Krishna",1));
		customerList.add(new Customer(3331l,"Mani",2));
		customerList.add(new Customer(3332l,"Velu",3));
		customerList.add(new Customer(3333l,"Raja",1));
		customerList.add(new Customer(3334l,"Velan",2));
		customerList.add(new Customer(3335l,"Vijay",4));
			
//Adding product->Book details
		List <Product>productList=new ArrayList<Product>();
		productList.add(new Product(936133l,"Agni Siragugal","Books",1000d));
		productList.add(new Product(976545l,"Unfinished","Books",1200d));
		productList.add(new Product(987637l,"Thirukkural","Books",2000d));
		productList.add(new Product(936133l,"Brave New World","Books",700d));
		productList.add(new Product(936133l,"Pale Fire","Books",90d));
		productList.add(new Product(936133l,"Mortal Engines","Books",1400d));

//Adding product->Toys details
		productList.add(new Product(1000l,"Doll","Toys",600d));
		productList.add(new Product(1001l,"Toy car","Toys",400d));
		productList.add(new Product(1002l,"Rubber Duck","Toys",1000d));
		productList.add(new Product(1003l,"Teddy Bear","Toys",1200d));
		productList.add(new Product(1004l,"Sand Bucket","Toys",800d));
		productList.add(new Product(1005l,"Balloon","Toys",100d));
	
//Adding Product->Baby details
		productList.add(new Product(101l,"Clothes","Baby",300d));
		productList.add(new Product(102l,"Diaper","Baby",100d));
		productList.add(new Product(103l,"Swaddling blanket","Baby",500d));
		productList.add(new Product(104l,"crib","Baby",250d));
		productList.add(new Product(104l,"dress","Baby",800d));
	
//Adding Order Details
				List <Order>orderList=new ArrayList<Order>();
		       orderList.add(new Order(22225l,"Deliverd",LocalDate.of(2021,02,01),LocalDate.of(2021,04,01),productList,customer));
		       orderList.add(new Order(22226l,"Pending",LocalDate.of(2023, 07, 19),LocalDate.of(2023, 05, 24), productList,customer));
		       orderList.add(new Order(22227l,"Shipped",LocalDate.of(2023, 05, 27),LocalDate.of(2023, 06, 01), productList,customer));
		       orderList.add(new Order(22228l,"Pending",LocalDate.of(2023, 05, 21),LocalDate.of(2023, 05, 25), productList,customer));
		       orderList.add(new Order(22229l,"Deliverd",LocalDate.of(2023, 05, 16),LocalDate.of(2023, 05, 20), productList,customer));
		       orderList.add(new Order(22230l,"Shipped",LocalDate.of(2021, 03, 15),LocalDate.of(2021, 03, 20), productList,customer));
				
					
		
//1.Obtain a list of products belongs to category “Books” with price > 100
		
	   List<Product> expensiveBooks = productList.stream()
			.filter(product -> product.getCategory().equals("Books"))
		    .filter(product -> product.getPrice() > 100)
			.collect(Collectors.toList());
		System.out.println("Books:"+expensiveBooks);
		
//OR
		
		List<Boolean> books = productList.stream()
			.filter(product -> product.getCategory().equalsIgnoreCase("Books"))
		    .map(product -> product.getPrice() > 100)
		    .collect(Collectors.toList());	
		System.out.println("Books:"+books);
	

//2.Obtain a list of order with products belong to category “baby”
		List<Product>orderBabyList=productList.stream()
		    .filter(baby -> baby.getCategory().equals("Baby"))//filtering data
			.collect(Collectors.toList()); //collecting as list 
		    System.out.println(orderBabyList);
			
//3.Obtain a list of product with category = “Toys” and then apply 10% discount

	   List<Product> discountProducts = productList.stream()
	        .filter(discount -> discount.getCategory().equals("Toys"))
	        .peek(discount -> discount.setPrice(discount.getPrice() * 0.9)) // Apply 10% discount
	        .collect(Collectors.toList());	
	    System.out.println(discountProducts);

//4.Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021
	    List<Product> orderedProducts = orderList.stream()
	            .filter(order -> order.getOrderDate().isAfter(LocalDate.of(2023, 1, 31)) &&
	                             order.getOrderDate().isBefore(LocalDate.of(2021, 4, 2)))
	            .filter(order -> order.getOrderId() == customer.getCustomerId() &&
	                             customer.getTier() == 2)
	            .flatMap(order -> order.getProducts().stream())
	            .collect(Collectors.toList());
	        
	        // Display the ordered products
	        
	        System.out.println("Products ordered by tier 2 customers between 01-Feb-2021 and 01-Apr-2021:"+orderedProducts);
	
//5.Get the cheapest products of “Books” category    
	        Optional<Product> cheapestBook = productList
	                .stream()
	                .filter(p -> p.getCategory().equalsIgnoreCase("Books"))
	                .sorted(Comparator.comparing(Product::getPrice))
	                .findFirst();
	        
	        System.out.println("Cheapest Book:"+cheapestBook);
	        
//6.Get the 3 most recent placed order    
	        List<Order> recent = orderList
	                .stream()
	                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
	                .limit(3)
	                .collect(Collectors.toList());
	        System.out.println("Recent Order:"+recent);

//7). Get a list of orders which were ordered on 15-Mar-2021, log the order records to the console and then return its product list
	        List<Order> records = orderList.stream()
	                .filter(order -> order.getOrderDate().equals(LocalDate.parse("15-03-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy"))))
	                .peek(order -> System.out.println("Order ID: " + order.getOrderId() + ", Products: " + order.getStatus()))
	                .collect(Collectors.toList());
	        System.out.println("Product list:"+records);
	        

//8.Calculate total lump sum of all orders placed in Feb 2021
	        Double sum = orderList
	        	    .stream()
	        	    .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
	        	    .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 3, 1)) < 0)
	        	    .flatMap(order -> order.getProducts().stream())
	        	    .mapToDouble(p -> p.getPrice())
	        	    .sum();	  
	        System.out.println("sum of all orders:"+sum);
	        
//9.Calculate order average payment placed on 14-Mar-2021
	        OptionalDouble averageList = orderList
	        	    .stream()
	        	    .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
	        	    .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 3, 1)) < 0)
	        	    .flatMap(order -> order.getProducts().stream())
	        	    .mapToDouble(p -> p.getPrice())
	        	    .average();	  
	        System.out.println("Calculate order average payment:"+averageList);
	        
//10.Obtain a data map with order id and order’s product count
	        Map<Long, Object>  count = orderList
	                .stream()
	                .collect(
	                    Collectors.toMap(
	                        order -> order.getOrderId(),
	                        order -> order.getProducts().size()
	                        )
	                    ); System.out.println(count);
	                    
  //11). Get the most expensive product by category
	 
	    Map<String, Optional<Product>> mostExpensiveByCategory = productList
	            .stream()
	            .collect(
	            Collectors.groupingBy(
	            Product::getCategory,
	            Collectors.maxBy(Comparator.comparing(Product::getPrice))));
	    
	System.out.println("Expensive Category:"+mostExpensiveByCategory);	                    
	}
}
