package src;

import java.util.List;

public class Ejercicio13{
    public static void main(String[] args) {
        
        List<Integer> nums = List.of(7, 11, 13, 20, 25);
        boolean par  = nums.stream().anyMatch(num -> num % 2 == 0);
        System.out.println("Hay algun par? " + par);
        
    }
}