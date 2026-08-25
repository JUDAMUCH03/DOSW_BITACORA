package dosw.semana1.streams;

import java.util.List;

public class Ejercicio06 {
    public static void main(String[] args) {
        
        List<String> empleados = List.of("Laura", "Pedro", "Carlos", "Ana");
        List<String> empleadosMayus = empleados.stream().map(empleado -> empleado.toUpperCase()).peek(emp -> System.out.println("Transformado: " + emp)).toList();
        System.out.println("Lista: " + empleadosMayus);
        
    }
}