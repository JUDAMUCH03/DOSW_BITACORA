package dosw.semana2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RetoMewtwo {

    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
                new Pokemon(1L, "Pikachu", "Eléctrico", 25, 320.0, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psíquico", 70, 680.0, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragón", 55, 530.0, "Kanto", false),
                new Pokemon(4L, "Caterpie", "Bicho", 10, 95.0, "Kanto", false),
                new Pokemon(5L, "Tyranitar", "Roca", 60, 600.0, "Johto", false),
                new Pokemon(6L, "Totodile", "Agua", 15, 180.0, "Johto", false),
                new Pokemon(7L, "Rayquaza", "Dragón", 75, 710.0, "Hoenn", true),
                new Pokemon(8L, "Torchic", "Fuego", 12, 160.0, "Hoenn", false),
                new Pokemon(9L, "Lucario", "Lucha", 50, 520.0, "Sinnoh", false)
        );

        //filter(), sorted() y groupingBy()
        Map<String, List<String>> clasificadosPorRegion = pokedex.stream()
                .filter(p -> p.getNivel() >= 25)
                .sorted(Comparator.comparingDouble(Pokemon::getPoderCombate).reversed())
                .collect(Collectors.groupingBy(
                        Pokemon::getRegion,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())
                ));

        //map() y reduce()
        double poderTotalTorneo = pokedex.stream()
                .filter(p -> p.getNivel() >= 25)
                .map(Pokemon::getPoderCombate)
                .reduce(0.0, Double::sum);

        System.out.println("CLASIFICADOS AL TORNEO SUPREMO (POR REGIÓN)");
        clasificadosPorRegion.forEach((region, pokemones) -> 
                System.out.println(region + ":\t" + pokemones));
        System.out.println("Poder total acumulado de clasificados: " + (int) poderTotalTorneo);
    }
}