package dosw.semana1.streams;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Ejercicio05 {
    public static void main(String[] args) {
        
        List<String> correos = List.of("a@correo.com", "b@correo.com", "a@correo.com", "c@correo.com", "b@correo.com");
        Set<String> correosNoRepetidos = correos.stream().collect(Collectors.toSet());
        System.out.println("set con " + correosNoRepetidos.size() +  " elementos unicos: " + correosNoRepetidos);
        
    }
}