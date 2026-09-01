package dosw.semana1.streams;

import java.util.List;

public class TallerEjercicio02 {
    public static void main(String[] args) {
        List<String> palabras = List.of("java", "stream", "api", "functional", "code", "git");

        long cantidad = palabras.stream()
                .filter(p -> p.length() > 4)
                .map(String::toUpperCase)
                .sorted()
                .count();

        System.out.println("Palabras de entrada: " + palabras);
        System.out.println("Cantidad de palabras resultantes: " + cantidad);
    }
}