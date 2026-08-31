package dosw.semana2.pokemon;
import java.util.List;

public class Ejercicio7 {

    public static void main(String[] args) {
        List<String> pokedex = List.of(
                "Squirtle", "Pikachu", "Mewtwo",
                "Bulbasaur", "Charmander", "Abra"
        );

        List<String> ordenados = pokedex.stream()
                .sorted()
                .toList();

        System.out.println(ordenados);
    }
}