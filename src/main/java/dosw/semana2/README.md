# SEMANA No 2 — Bitácora Pokémon

## Datos de Entrenador:
- **Nombre y Apellido:** Juan Munar
- **Código de Estudiante:** 1000103253 
- **Curso:** DOSW

---

### Ejercicio 01 — Pokémon Tipo Fuego
**Enunciado:**
Dada una lista de Pokémon con nombre y tipo, obtener únicamente aquellos cuyo tipo sea Fuego.
- **Datos de entrada:** `Pikachu(Eléctrico), Charmander(Fuego), Squirtle(Agua), Vulpix(Fuego), Bulbasaur(Planta), Flareon(Fuego)`
- **Salida esperada:** `[Charmander, Vulpix, Flareon]`

**Código implementado:**
```java
package dosw.semana2.pokemon;

import java.util.List;

public class Ejercicio1 {

    public static class Pokemon {
        private String nombre;
        private String tipo;

        public Pokemon(String nombre, String tipo) {
            this.nombre = nombre;
            this.tipo = tipo;
        }

        public String getNombre() {
            return nombre;
        }

        public String getTipo() {
            return tipo;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        @Override
        public String toString() {
            return nombre + " (" + tipo + ")";
        }
    }

    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
                new Pokemon("Pikachu", "Eléctrico"),
                new Pokemon("Charmander", "Fuego"),
                new Pokemon("Squirtle", "Agua"),
                new Pokemon("Vulpix", "Fuego"),
                new Pokemon("Bulbasaur", "Planta"),
                new Pokemon("Flareon", "Fuego")
        );

        List<String> pokemonesFuego = pokedex.stream().filter(p -> p.getTipo().equalsIgnoreCase("Fuego")).map(Pokemon::getNombre).toList();
        System.out.println(pokemonesFuego);
    }
}
```

Captura:
<img width="232" height="26" alt="image" src="https://github.com/user-attachments/assets/0d4bcdf6-e251-4170-b158-987ddab8aa19" />


Explicación:
La operación  filter() evalúa un predicado para conservar exclusivamente las instancias cuyo atributo tipo coincide con "Fuego". Posteriormente, map(Pokemon::getNombre) proyecta el flujo de objetos al tipo String utilizando un Method Reference (::) y recolecta el resultado final en una lista inmutable mediante .toList().

### Ejercicio 02 — Pokédex Gritona
**Enunciado:**
Dada una lista con nombres de Pokémon, transformar todos los elementos a letras mayúsculas utilizando la operación intermedia `map()`.
- **Datos de entrada:** `["Pikachu", "Charmander", "Squirtle", "Bulbasaur"]`
- **Salida esperada:** `[PIKACHU, CHARMANDER, SQUIRTLE, BULBASAUR]`

**Código implementado:**
```java
package dosw.semana2.pokemon;

import java.util.List;

public class Ejercicio2 {

    public static void main(String[] args) {
        List<String> pokemons = List.of("Pikachu", "Charmander", "Squirtle", "Bulbasaur");
        List<String> pokemonsMayus = pokemons.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println(pokemonsMayus);
    }
}
```

Captura:


Explicación:
La operación intermedia map() realiza una transformación 1:1 sobre cada elemento del flujo aplicando String::toUpperCase a través de un Method Reference (::) sin mutar la lista original. Finalmente, el operador terminal .toList() recolecta el flujo transformado en una nueva colección inmutable.
