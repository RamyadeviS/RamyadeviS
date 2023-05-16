package streamstest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import streamspractice.Example1;

public class ExampleTest1 {
    public static void main(String[] args) {  
        List<Example1> productsList = new ArrayList<Example1>();  
        //Adding Products  
        productsList.add(new Example1(1,"HP Laptop",25000f));  
        productsList.add(new Example1(2,"Dell Laptop",30000f));  
        productsList.add(new Example1(3,"Lenevo Laptop",28000f));  
        productsList.add(new Example1(4,"Sony Laptop",28000f));  
        productsList.add(new Example1(5,"Apple Laptop",90000f));  
        List<Integer> productPriceList2 =productsList.stream()  
                                     .filter(p -> p.getId() >2)// filtering data  
                                     .map(p->p.getId())        // fetching price  
                                     .collect(Collectors.toList()); // collecting as list  
        System.out.println(productPriceList2);  
    }  
}  

