package dosw.semana1.streams;

import java.util.List;

public class Ejercicio04 {
    public static void main(String[] args) {
        
        List<Integer> nums = List.of(12, 8, 5, 10, 15);
        int suma = nums.stream().reduce(0, (total, num) -> total + num);
        System.out.println("suma = " + suma);
        
    }
}