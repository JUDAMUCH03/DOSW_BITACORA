package dosw.semana1.streams;

import java.util.List;

public class Ejercicio09 {
    public static void main(String[] args) {
        
        List<Integer> puntajes = List.of(98, 95, 92, 89, 84, 81, 78, 73, 69, 65, 62, 56, 51, 48, 42, 31, 27, 23, 11, 4);
        List<Integer> top = puntajes.stream().limit(5).toList();
        System.out.println("Top 5 puntajes: " + top);
        
    }
}