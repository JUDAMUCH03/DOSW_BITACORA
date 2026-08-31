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

<img width="328" height="23" alt="Captura de pantalla 2026-08-31 134834" src="https://github.com/user-attachments/assets/066304e7-b782-4cd9-8f6e-bdfcd3292181" />


Explicación:
La operación intermedia map() realiza una transformación 1:1 sobre cada elemento del flujo aplicando String::toUpperCase a través de un Method Reference (::) sin mutar la lista original. Finalmente, el operador terminal .toList() recolecta el flujo transformado en una nueva colección inmutable.


### Ejercicio 03 — Poder Total del Equipo
**Enunciado:**
Dada una lista de niveles de Pokémon, calcular la suma total de niveles del equipo utilizando la operación terminal `reduce()`.
- **Datos de entrada:** `niveles: [45, 62, 38, 71, 55, 29]`
- **Salida esperada:** `Suma total de niveles: 300`

**Código implementado:**
```java
package dosw.semana2.pokemon;

import java.util.List;

public class Ejercicio3 {

    public static void main(String[] args) {
        List<Integer> niveles = List.of(45, 62, 38, 71, 55, 29);

        int sumaTotal = niveles.stream()
                .reduce(0, Integer::sum);

        System.out.println("Suma total de niveles: " + sumaTotal);
    }
}
```

Captura:

<img width="210" height="21" alt="Captura de pantalla 2026-08-31 135544" src="https://github.com/user-attachments/assets/82926bd0-72d3-417b-aa70-288648b84d94" />


Explicación:
La operación terminal `reduce()` combina todos los elementos del flujo en un único valor acumulado partiendo del elemento neutro `0`. Se utiliza el Method Reference `Integer::sum` como operador binario asociativo, garantizando la inmutabilidad y sumando avance al Reto Legendario.

---

### Ejercicio 04 — Pokémon Alfa
**Enunciado:**
Encontrar el Pokémon con el nivel más alto dentro del equipo utilizando `max(Comparator)`.
- **Datos de entrada:** `Pikachu(45), Charmander(62), Squirtle(38), Snorlax(90), Mewtwo(88)`
- **Salida esperada:** `Pokémon Alfa: Snorlax (nivel 90)`

**Código implementado:**
```java
package dosw.semana2.pokemon;

import java.util.Comparator;
import java.util.List;

public class Ejercicio4 {

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
            return nombre + " (nivel " + nivel + ")";
        }
    }

    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
                new Pokemon("Pikachu", 45),
                new Pokemon("Charmander", 62),
                new Pokemon("Squirtle", 38),
                new Pokemon("Snorlax", 90),
                new Pokemon("Mewtwo", 88)
        );

        equipo.stream()
                .max(Comparator.comparingInt(Pokemon::getNivel))
                .ifPresent(alfa -> System.out.println("Pokémon Alfa: " + alfa.getNombre() + " (nivel " + alfa.getNivel() + ")"));
    }
}
```

Captura:

<img width="231" height="14" alt="Captura de pantalla 2026-08-31 135559" src="https://github.com/user-attachments/assets/296d0626-af06-478c-99ef-56495357fe53" />


Explicación:
La operación terminal max() evalúa el flujo a partir de una función de comparación generada por Comparator.comparingInt(Pokemon::getNivel). Retorna un contenedor seguro Optional<Pokemon> que evita excepciones NullPointerException en colecciones vacías y se consume de forma funcional mediante .ifPresent().

---

### Ejercicio 05 — Pokémon Legendarios
**Enunciado:**
Contar cuántos Pokémon del equipo tienen nivel superior a 80 utilizando la combinación de `filter()` y `count()`.
- **Datos de entrada:** `Pikachu(45), Mewtwo(88), Dragonite(82), Squirtle(38), Mew(85), Charmander(62)`
- **Salida esperada:** `Pokémon con nivel > 80: 3 (Mewtwo, Dragonite, Mew)`

**Código implementado:**
```java
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

        long cantidadLegendarios = equipo.stream()
                .filter(p -> p.getNivel() > 80)
                .count();

        List<String> nombresLegendarios = equipo.stream()
                .filter(p -> p.getNivel() > 80)
                .map(Pokemon::getNombre)
                .toList();

        System.out.println("Pokémon con nivel > 80: " + cantidadLegendarios);
        System.out.println("(" + String.join(", ", nombresLegendarios) + ")");
    }
}
```

Captura:

<img width="195" height="40" alt="Captura de pantalla 2026-08-31 135616" src="https://github.com/user-attachments/assets/905b0cf5-4aca-44a9-8130-6e488baef717" />


Explicación:
La operación intermedia filter() evalúa el predicado p.getNivel() > 80 aislando los registros que cumplen la regla de negocio. La operación terminal count() procesa la cardinalidad de los elementos resultantes retornando un valor long. De forma complementaria, se proyectan los nombres filtrados con map(Pokemon::getNombre) para desglosar la salida esperada.


### Ejercicio 06 — Pokédex Sin Duplicados
**Enunciado:**
Dada una lista de Pokémon con elementos repetidos, generar una nueva colección donde cada Pokémon aparezca una sola vez utilizando `distinct()`.
- **Datos de entrada:** `Pikachu, Charmander, Pikachu, Squirtle, Charmander, Mewtwo`
- **Salida esperada:** `[Pikachu, Charmander, Squirtle, Mewtwo]`

**Código implementado:**
```java
package dosw.semana2.pokemon;

import java.util.List;

public class Ejercicio6 {

    public static void main(String[] args) {
        List<String> pokemons = List.of(
                "Pikachu", "Charmander", "Pikachu",
                "Squirtle", "Charmander", "Mewtwo"
        );

        List<String> sinDuplicados = pokemons.stream()
                .distinct()
                .toList();

        System.out.println(sinDuplicados);
    }
}
```

Captura:

<img width="295" height="25" alt="Captura de pantalla 2026-08-31 143936" src="https://github.com/user-attachments/assets/326c4ccd-f06c-4b2c-9992-b4ea1de480b7" />


Explicación:
La operación intermedia con estado (`stateful intermediate operation`) `distinct()` procesa el flujo eliminando elementos duplicados según su implementación de `equals()` y `hashCode()`. Mantiene el orden de inserción original del stream y recopila el resultado limpio en una lista inmutable con `.toList()`.

---

### Ejercicio 07 — Orden del Profesor Oak
**Enunciado:**
El Profesor Oak quiere su Pokédex organizada. Ordenar alfabéticamente los nombres de los Pokémon utilizando `sorted()`.
- **Datos de entrada:** `Squirtle, Pikachu, Mewtwo, Bulbasaur, Charmander, Abra`
- **Salida esperada:** `[Abra, Bulbasaur, Charmander, Mewtwo, Pikachu, Squirtle]`

**Código implementado:**
```java
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
```

Captura:

<img width="412" height="28" alt="Captura de pantalla 2026-08-31 144001" src="https://github.com/user-attachments/assets/359119e9-f620-4a52-9a7b-f3416fad10e2" />


Explicación:
La operación intermedia `sorted()` evalúa los elementos bajo su orden natural (*Comparable* / orden lexicográfico de cadenas ASCII/Unicode). Al ser una operación con estado, amortigua los elementos del flujo hasta completar la secuencia y los emite ordenados de forma ascendente antes de ser consolidados con `.toList()`.

---

### Ejercicio 08 — Evoluciones Preparadas
**Enunciado:**
Dada una lista de Pokémon que incluye si pueden evolucionar (`boolean puedeEvolucionar`), obtener únicamente los que estén listos para evolucionar utilizando `filter()`.
- **Datos de entrada:** `Pikachu(true), Raichu(false), Charmander(true), Charizard(false), Squirtle(true), Blastoise(false)`
- **Salida esperada:** `Listos para evolucionar: [Pikachu, Charmander, Squirtle]`

**Código implementado:**
```java
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
```

Captura:

<img width="231" height="40" alt="Captura de pantalla 2026-08-31 144010" src="https://github.com/user-attachments/assets/a5972cc8-a941-49cb-92fb-434658137a1b" />


Explicación:
Se utiliza `filter(Pokemon::isPuedeEvolucionar)` empleando un Method Reference como predicado funcional para retener únicamente los especímenes con bandera activa. Luego, `map(Pokemon::getNombre)` desacopla el modelo transformando el stream a cadenas con los nombres correspondientes y `.toList()` construye la colección inmutable resultante.

