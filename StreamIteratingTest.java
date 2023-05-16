package streamstest;

import java.util.stream.Stream;

public class StreamIteratingTest {
    public static void main(String[] args){  
 Stream.iterate(1,element ->element+1)
 .filter(element ->element%11==0)
 .limit(6)
 .forEach(System.out::println); 
}
}