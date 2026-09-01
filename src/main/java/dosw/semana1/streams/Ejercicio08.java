package dosw.semana1.streams;

import java.util.List;

public class Ejercicio08 {
    public static void main(String[] args) {
        
        List<String> codigos = List.of("P01","P02","P01","P03","P02","P04");
        List<String> codigosNoRep = codigos.stream().distinct().toList();
        System.out.println(codigosNoRep);
        
    }
}