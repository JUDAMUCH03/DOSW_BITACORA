package src;

import java.util.List;

public class Ejercicio03 {
    public static void main(String[] args) {
        
        List<String> ciudades = List.of("Bogotá", "Medellín", "Cali", "Barranquilla");
        List<String> ciudadesMayus = ciudades.stream().map(ciudad -> ciudad.toUpperCase()).toList();
        System.out.println(ciudadesMayus);
        
    }
}