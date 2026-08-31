package dosw.semana2.pokemon;

import java.util.Comparator;
import java.util.List;

public class Ejercicio17 {
    public static void main(String[] args) {
        List<Pokemon> equipoAsh = List.of(
                new Pokemon(1L, "Pikachu", "Eléctrico", 50, 650.0, "Kanto", false),
                new Pokemon(2L, "Charizard", "Fuego", 55, 700.0, "Kanto", false),
                new Pokemon(3L, "Sceptile", "Planta", 45, 500.0, "Hoenn", false)
        );

        List<Pokemon> equipoGary = List.of(
                new Pokemon(4L, "Blastoise", "Agua", 60, 800.0, "Kanto", false),
                new Pokemon(5L, "Arcanine", "Fuego", 58, 780.0, "Kanto", false),
                new Pokemon(6L, "Umbreon", "Siniestro", 56, 760.0, "Johto", false)
        );

        List<Pokemon> equipoBrock = List.of(
                new Pokemon(7L, "Onix", "Roca", 40, 550.0, "Kanto", false),
                new Pokemon(8L, "Geodude", "Roca", 38, 520.0, "Kanto", false),
                new Pokemon(9L, "Steelix", "Acero", 48, 600.0, "Johto", false)
        );

        List<Entrenador> entrenadores = List.of(
                new Entrenador(1L, "Ash", 8, equipoAsh),
                new Entrenador(2L, "Gary", 10, equipoGary),
                new Entrenador(3L, "Brock", 6, equipoBrock)
        );

        Entrenador masPoderoso = entrenadores.stream()
                .max(Comparator.comparingDouble(e -> e.getEquipo().stream()
                        .mapToDouble(Pokemon::getPoderCombate)
                        .sum()))
                .get();

        double poderTotal = masPoderoso.getEquipo().stream()
                .mapToDouble(Pokemon::getPoderCombate)
                .sum();

        System.out.println("Entrenador más poderoso: " + masPoderoso.getNombre());
        System.out.println("Poder acumulado del equipo: " + (int) poderTotal);
    }
}