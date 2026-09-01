package dosw.semana1.streams;

import java.util.Comparator;
import java.util.List;

public class RetoFinalIntegrador {

    public record Estudiante(String nombre, double promedio) {}
    public static void main(String[] args) {
        
         List<Estudiante> estudiantes = List.of(new Estudiante("Ana",4.5 ), new Estudiante("Carlos", 3.2),
                new Estudiante("Pedro", 2.8), new Estudiante("Laura", 4.8),
                new Estudiante("Andres", 3.9), new Estudiante("Maria", 2.5));

        List<String> listaFinal = estudiantes.stream().filter(e -> e.promedio() >= 3.0).sorted(Comparator.comparingDouble(Estudiante::promedio).reversed())
                .map(e -> new Estudiante(e.nombre().toUpperCase(), e.promedio()))
                .peek(e -> System.out.println("Procesado: " + e.nombre() + " " + e.promedio())).map(e -> e.nombre()+"("+e.promedio()+")").toList();


        System.out.println();
        System.out.println("Lista final: " + listaFinal);
        
    }
}