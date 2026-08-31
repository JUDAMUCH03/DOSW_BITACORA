package dosw.semana2.pokemon;

import java.util.List;

public class Ejercicio11 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
                new Pokemon(1L, "Pikachu", "Eléctrico", 25, 320.0, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psíquico", 70, 680.0, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragón", 55, 530.0, "Kanto", false),
                new Pokemon(4L, "Squirtle", "Agua", 15, 210.0, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 45, 495.0, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 60, 610.0, "Kanto", false)
        );

        double promedio = equipo.stream()
                .mapToDouble(Pokemon::getPoderCombate).average().orElse(0.0);

        System.out.printf("Poder de combate promedio: %.2f\n", promedio);
    }
}