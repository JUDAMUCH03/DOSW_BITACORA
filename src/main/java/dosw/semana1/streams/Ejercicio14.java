package dosw.semana1.streams;

import java.util.List;

public class Ejercicio14{
    public static void main(String[] args) {
        
        List<Double> notas = List.of(4.0, 3.5, 4.2, 5.0, 3.8);
        boolean mayor3 = notas.stream().allMatch(nota -> nota >= 3);
        System.out.println("¿Todas >= 3.0? " + mayor3);
        
    }
}