package src;

import java.util.List;

public class Ejercicio10 {
    public static void main(String[] args) {
        
        List<String> peliculas = List.of("Avatar", "Titanic", "Interestelar", "Matrix", "Gladiador");
        List<String> nuevaLista = peliculas.stream().skip(2).toList();
        System.out.println(nuevaLista);
        
    }
}