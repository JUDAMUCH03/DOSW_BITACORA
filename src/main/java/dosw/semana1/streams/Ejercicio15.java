package src;

import java.util.List;

public class Ejercicio15{
    public static void main(String[] args) {
        
        List<String> usuarios = List.of("juan", "maria", "admin", "pedro", "soporte");
        boolean userRoot = usuarios.stream().noneMatch(usuario -> "root".equals(usuario));
        System.out.println("¿Ninguno es \"root\"? " + userRoot);
        
    }
}