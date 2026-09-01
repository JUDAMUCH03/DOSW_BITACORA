package dosw.semana2.pokemon;
import java.util.List;

public class Ejercicio8 {

    public static class Pokemon {
        private String nombre;
        private boolean puedeEvolucionar;

        public Pokemon(String nombre, boolean puedeEvolucionar) {
            this.nombre = nombre;
            this.puedeEvolucionar = puedeEvolucionar;
        }

        public String getNombre() { return nombre; }
        public boolean isPuedeEvolucionar() { return puedeEvolucionar; }

        public void setNombre(String nombre) { this.nombre = nombre; }
        public void setPuedeEvolucionar(boolean puedeEvolucionar) { this.puedeEvolucionar = puedeEvolucionar; }

        @Override
        public String toString() {
            return nombre + "(" + puedeEvolucionar + ")";
        }
    }

    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
                new Pokemon("Pikachu", true),
                new Pokemon("Raichu", false),
                new Pokemon("Charmander", true),
                new Pokemon("Charizard", false),
                new Pokemon("Squirtle", true),
                new Pokemon("Blastoise", false)
        );

        List<String> listosParaEvolucionar = equipo.stream()
                .filter(Pokemon::isPuedeEvolucionar)
                .map(Pokemon::getNombre)
                .toList();

        System.out.println("Listos para evolucionar:");
        System.out.println(listosParaEvolucionar);
    }
}