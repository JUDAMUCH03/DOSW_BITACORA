package dosw.semana2.pokemon;

import java.util.List;

public class Ejercicio5 {

    public static class Pokemon {
        private String nombre;
        private int nivel;

        public Pokemon(String nombre, int nivel) {
            this.nombre = nombre;
            this.nivel = nivel;
        }

        public String getNombre() { return nombre; }
        public int getNivel() { return nivel; }

        public void setNombre(String nombre) { this.nombre = nombre; }
        public void setNivel(int nivel) { this.nivel = nivel; }

        @Override
        public String toString() {
            return nombre + " (" + nivel + ")";
        }
    }

    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
                new Pokemon("Pikachu", 45),
                new Pokemon("Mewtwo", 88),
                new Pokemon("Dragonite", 82),
                new Pokemon("Squirtle", 38),
                new Pokemon("Mew", 85),
                new Pokemon("Charmander", 62)
        );

        long cantidadLegendarios = equipo.stream().filter(p -> p.getNivel() > 80).count();

        List<String> nombresLegendarios = equipo.stream().filter(p -> p.getNivel() > 80).map(Pokemon::getNombre).toList();

        System.out.println("Pokémon con nivel > 80: " + cantidadLegendarios);
        System.out.println("(" + String.join(", ", nombresLegendarios) + ")");
    }
}