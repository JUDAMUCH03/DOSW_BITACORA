package dosw.semana1.streams;

import java.util.List;

public class Ejercicio02 {
    public static void main(String[] args) {
        
        List<String> productos = List.of("Laptop", "Mouse", "Teclado", "Monitor", "Impresora");
        productos.stream().forEach(producto -> System.out.println("Producto disponible: " + producto));
    
    }
}