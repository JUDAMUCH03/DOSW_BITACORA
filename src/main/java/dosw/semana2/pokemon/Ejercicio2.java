package dosw.semana2.pokemon;
import java.util.List;

public class Ejercicio2 {

    public static void main(String[] args) {
        List<String> pokemons = List.of("Pikachu", "Charmander","Squirtle", "Bulbasaur");
        List<String> pokemonsMayus = pokemons.stream().map(String::toUpperCase).toList();
        System.out.println(pokemonsMayus);
    }
}