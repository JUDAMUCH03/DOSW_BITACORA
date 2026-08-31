package dosw.semana2.pokemon;

import java.util.Comparator;
import java.util.List;

public class Ejercicio15 {
    public static void main(String[] args) {
        List<Entrenador> entrenadores = List.of(
                new Entrenador(1L, "Ash", 8, List.of()),
                new Entrenador(2L, "Misty", 5, List.of()),
                new Entrenador(3L, "Brock", 6, List.of()),
                new Entrenador(4L, "Gary", 10, List.of())
        );

        Entrenador campeon = entrenadores.stream()
                .max(Comparator.comparingInt(Entrenador::getMedallas))
                .get();

        System.out.println("Campeón de gimnasios: " + campeon.getNombre());
        System.out.println("Medallas obtenidas: " + campeon.getMedallas());
    }
}