package dosw.semana1.streams;

import java.util.Comparator;
import java.util.List;

public class Ejercicio12 {
    public static void main(String[] args) {
        
        List<Integer> salarios = List.of(1800000, 2500000, 3200000, 2100000, 4000000);
        int max = salarios.stream().max(Comparator.naturalOrder()).orElseThrow();
        System.out.println("Salario maximo: " + max);
        
    }
}