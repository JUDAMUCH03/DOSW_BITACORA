package dosw.semana2.pokemon;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ejercicio14 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
                new Pokemon(1L, "Pikachu", "Eléctrico", 25, 320.0, "Kanto", false),
                new Pokemon(2L, "Chikorita", "Planta", 15, 210.0, "Johto", false),
                new Pokemon(3L, "Torchic", "Fuego", 16, 230.0, "Hoenn", false),
                new Pokemon(4L, "Piplup", "Agua", 14, 205.0, "Sinnoh", false),
                new Pokemon(5L, "Charmander", "Fuego", 16, 250.0, "Kanto", false),
                new Pokemon(6L, "Totodile", "Agua", 15, 220.0, "Johto", false)
        );

        Map<String, List<String>> porRegion = equipo.stream()
                .collect(Collectors.groupingBy(
                        Pokemon::getRegion,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())
                ));

        porRegion.forEach((region, lista) -> System.out.println(region + ":\t" + lista));
    }
}