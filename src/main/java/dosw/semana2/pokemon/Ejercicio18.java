package dosw.semana2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Ejercicio18 {
    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
                new Pokemon(1L, "Pikachu", "Eléctrico", 25, 320.0, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psíquico", 70, 680.0, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragón", 55, 530.0, "Kanto", false),
                new Pokemon(4L, "Squirtle", "Agua", 15, 210.0, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 45, 495.0, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 60, 610.0, "Kanto", false)
        );

        List<Pokemon> top5 = pokedex.stream()
                .sorted(Comparator.comparingDouble(Pokemon::getPoderCombate).reversed())
                .limit(5)
                .toList();

        AtomicInteger ranking = new AtomicInteger(1);
        top5.forEach(p -> System.out.printf("#%d %-10s – PC: %d\n",
                ranking.getAndIncrement(), p.getNombre(), (int) p.getPoderCombate()));
    }
}