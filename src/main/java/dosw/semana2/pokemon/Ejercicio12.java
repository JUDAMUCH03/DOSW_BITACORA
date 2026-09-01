package dosw.semana2.pokemon;

import java.util.Comparator;
import java.util.List;

public class Ejercicio12 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
                new Pokemon(1L, "Pikachu", "Eléctrico", 25, 320.0, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psíquico", 70, 680.0, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragón", 55, 530.0, "Kanto", false),
                new Pokemon(4L, "Charizard", "Fuego", 60, 610.0, "Kanto", false)
        );

        Pokemon campeon = equipo.stream().max(Comparator.comparingDouble(Pokemon::getPoderCombate)).get();
        System.out.println("Campeón: " + campeon.getNombre() + " con PC: " + (int) campeon.getPoderCombate());
    }
}