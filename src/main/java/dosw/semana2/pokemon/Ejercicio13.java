package dosw.semana2.pokemon;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ejercicio13 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
                new Pokemon(1L, "Squirtle", "Agua", 15, 210.0, "Kanto", false),
                new Pokemon(2L, "Psyduck", "Agua", 18, 240.0, "Kanto", false),
                new Pokemon(3L, "Charmander", "Fuego", 16, 250.0, "Kanto", false),
                new Pokemon(4L, "Vulpix", "Fuego", 20, 290.0, "Kanto", false),
                new Pokemon(5L, "Bulbasaur", "Planta", 14, 200.0, "Kanto", false)
        );

        Map<String, List<String>> porTipo = equipo.stream().collect(Collectors.groupingBy(Pokemon::getTipo,Collectors.mapping(Pokemon::getNombre, Collectors.toList())
                ));

        porTipo.forEach((tipo, lista) -> System.out.println(tipo + ":\t" + lista));
    }
}