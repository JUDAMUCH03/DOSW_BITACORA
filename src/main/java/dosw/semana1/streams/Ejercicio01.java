package dosw.semana1.streams;

import java.util.List;

public class Ejercicio01 {
    public static void main(String[] args) {

        List<String> estudiantes = List.of("Ana", "Carlos", "Andres", "Pedro", "Alejandra", "Juan", "Amanda");
        List<String> estudiantesA = estudiantes.stream().filter(nombre -> nombre.startsWith("A")).toList();
        System.out.println(estudiantesA);
        
    }
}