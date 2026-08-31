package dosw.semana2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Ejercicio19 {
    public static void main(String[] args) {
        List<Entrenador> entrenadores = List.of(
                new Entrenador(1L, "Gary", 10, List.of(new Pokemon(1L, "Blastoise", "Agua", 60, 2340.0, "Kanto", false))),
                new Entrenador(2L, "Ash", 8, List.of(new Pokemon(2L, "Pikachu", "Eléctrico", 50, 1850.0, "Kanto", false))),
                new Entrenador(3L, "Dawn", 7, List.of(new Pokemon(3L, "Piplup", "Agua", 55, 2100.0, "Sinnoh", false))),
                new Entrenador(4L, "Brock", 6, List.of(new Pokemon(4L, "Onix", "Roca", 45, 1670.0, "Kanto", false)))
        );

        Comparator<Entrenador> rankingComparator = Comparator
                .comparingInt(Entrenador::getMedallas).reversed()
                .thenComparing(Comparator.comparingDouble((Entrenador e) -> e.getEquipo().stream()
                        .mapToDouble(Pokemon::getPoderCombate)
                        .sum()).reversed())
                .thenComparing(Entrenador::getNombre);

        List<Entrenador> top3 = entrenadores.stream()
                .sorted(rankingComparator)
                .limit(3)
                .toList();

        AtomicInteger ranking = new AtomicInteger(1);
        top3.forEach(e -> {
            double pcTotal = e.getEquipo().stream().mapToDouble(Pokemon::getPoderCombate).sum();
            System.out.printf("#%d %-5s – %d medallas, PC: %d\n",
                    ranking.getAndIncrement(), e.getNombre(), e.getMedallas(), (int) pcTotal);
        });
    }
}