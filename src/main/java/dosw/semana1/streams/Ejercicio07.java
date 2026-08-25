package src;

import java.util.List;
import java.util.Comparator;

public class Ejercicio07 {
    public static void main(String[] args) {
        
        List<Integer> edades = List.of(25, 18, 32, 21, 19, 28);
        List<Integer> edadesAsc = edades.stream().sorted().toList();
        List<Integer> edadesDes = edades.stream().sorted(Comparator.reverseOrder()).toList();

        System.out.println("Ascendente: " + edadesAsc);
        System.out.println("Descendente: " + edadesDes);
        
    }
}