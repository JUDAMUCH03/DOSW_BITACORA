package dosw.semana1.streams;

import java.util.Comparator;
import java.util.List;

public class Ejercicio11 {
    public static void main(String[] args) {
        
        List<Integer> precios = List.of(12000, 5000, 18000, 7500, 3000);
        int min = precios.stream().min(Comparator.naturalOrder()).orElseThrow();
        System.out.println("Precio minimo: " + min);
        
    }
}