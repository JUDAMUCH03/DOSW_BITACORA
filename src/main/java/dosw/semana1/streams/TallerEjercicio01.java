package dosw.semana1.streams;

import java.util.List;

public class TallerEjercicio01 {
    public static void main(String[] args) {
        List<Integer> numeros = List.of(3, 8, 10, 12, 15, 18, 20);

        List<Integer> resultado = numeros.stream()
                .filter(n -> n > 10 && n % 2 == 0)
                .toList();

        System.out.println("Entrada: " + numeros);
        System.out.println("Salida esperada: " + resultado);
    }
}