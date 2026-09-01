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

---

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

---

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

---

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

---

### Ejercicio 09 — Equipo Élite
**Enunciado:**
Mostrar únicamente los Pokémon cuyo poderCombate sea superior a 500 utilizando `filter()`.
- **Datos de entrada:** `Pikachu(PC:320), Mewtwo(PC:680), Dragonite(PC:530), Squirtle(PC:210), Gengar(PC:495), Charizard(PC:610)`
- **Salida esperada:**
  ```text
  Equipo Élite (PC > 500):
  [Mewtwo(680), Charizard(610), Dragonite(530)]
  ```

**Código implementado:**
```java
package dosw.semana2.pokemon;

import java.util.List;

public class Ejercicio9 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
                new Pokemon(1L, "Pikachu", "Eléctrico", 25, 320.0, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psíquico", 70, 680.0, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragón", 55, 530.0, "Kanto", false),
                new Pokemon(4L, "Squirtle", "Agua", 15, 210.0, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 45, 495.0, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 60, 610.0, "Kanto", false)
        );

        List<String> elite = equipo.stream()
                .filter(p -> p.getPoderCombate() > 500)
                .map(p -> p.getNombre() + "(" + (int) p.getPoderCombate() + ")")
                .toList();

        System.out.println("Equipo Élite (PC > 500):");
        System.out.println(elite);
    }
}
```

Captura:

<img width="330" height="41" alt="Captura de pantalla 2026-08-31 144018" src="https://github.com/user-attachments/assets/17bcb8ba-0802-4f42-b1f1-37b49f44cb14" />


Explicación:
La operación intermedia `filter()` evalúa el predicado numérico `p.getPoderCombate() > 500` descartando los elementos que no superan dicho umbral. Posteriormente, `map()` proyecta el formato requerido y `.toList()` recolecta el resultado en una nueva lista inmutable.

---

### Ejercicio 10 — Pokédex Compacta
**Enunciado:**
Generar una lista que contenga únicamente los nombres de todos los Pokémon del equipo utilizando `map() + collect()`.
- **Datos de entrada:** `Lista de objetos Pokemon completos`
- **Salida esperada:** `["Pikachu", "Mewtwo", "Dragonite", "Squirtle", "Gengar", "Charizard"]`

**Código implementado:**
```java
package dosw.semana2.pokemon;

import java.util.List;

public class Ejercicio10 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
                new Pokemon(1L, "Pikachu", "Eléctrico", 25, 320.0, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psíquico", 70, 680.0, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragón", 55, 530.0, "Kanto", false),
                new Pokemon(4L, "Squirtle", "Agua", 15, 210.0, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 45, 495.0, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 60, 610.0, "Kanto", false)
        );

        List<String> nombres = equipo.stream()
                .map(Pokemon::getNombre)
                .toList();

        System.out.println(nombres);
    }
}
```

Captura:

<img width="412" height="22" alt="Captura de pantalla 2026-08-31 144519" src="https://github.com/user-attachments/assets/94508bb9-1e9b-4909-861b-aa6b25176d20" />


Explicación:
La operación intermedia `map(Pokemon::getNombre)` realiza una transformación 1:1 extrayendo únicamente el atributo `nombre` mediante Method Reference (`::`). Finalmente, la operación terminal `.toList()` consolida los nombres en una colección inmutable.

---

### Ejercicio 11 — Poder Promedio
**Enunciado:**
Calcular el promedio de poderCombate de todos los Pokémon del equipo utilizando `mapToDouble() + average()`.
- **Datos de entrada:** `PC: [320, 680, 530, 210, 495, 610]`
- **Salida esperada:** `Poder de combate promedio: 474.17`

**Código implementado:**
```java
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
                .mapToDouble(Pokemon::getPoderCombate)
                .average()
                .orElse(0.0);

        System.out.printf("Poder de combate promedio: %.2f\n", promedio);
    }
}
```

Captura:

<img width="250" height="21" alt="Captura de pantalla 2026-08-31 144525" src="https://github.com/user-attachments/assets/99d10924-3510-4c63-8fd0-221b2a6003fc" />


Explicación:
`mapToDouble(Pokemon::getPoderCombate)` transforma el flujo a un `DoubleStream` primitivo evitando el sobrecosto de autoboxing. La operación terminal `average()` calcula la media aritmética de los valores numéricos retornando un `OptionalDouble`.

---

### Ejercicio 12 — Campeón Regional
**Enunciado:**
Obtener el Pokémon con mayor poderCombate de toda la lista utilizando `max(Comparator)`.
- **Datos de entrada:** `Pikachu(320), Mewtwo(680), Dragonite(530), Charizard(610)`
- **Salida esperada:** `Campeón: Mewtwo con PC: 680`

**Código implementado:**
```java
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

        Pokemon campeon = equipo.stream()
                .max(Comparator.comparingDouble(Pokemon::getPoderCombate))
                .get();

        System.out.println("Campeón: " + campeon.getNombre() + " con PC: " + (int) campeon.getPoderCombate());
    }
}
```

Captura:

<img width="202" height="20" alt="Captura de pantalla 2026-08-31 144529" src="https://github.com/user-attachments/assets/19e978f5-8d78-4e31-9987-293ff3f96bdf" />


Explicación:
La operación terminal `max()` evalúa los elementos mediante `Comparator.comparingDouble(Pokemon::getPoderCombate)` para identificar el objeto con el valor numérico más alto y `.get()` extrae la instancia encontrada.

---

### Ejercicio 13 — Organizar por Tipo
**Enunciado:**
Agrupar todos los Pokémon por su tipo y mostrar el listado por grupo utilizando `groupingBy()`.
- **Datos de entrada:** `Squirtle(Agua), Psyduck(Agua), Charmander(Fuego), Vulpix(Fuego), Bulbasaur(Planta)`
- **Salida esperada:**
  ```text
  Agua:   [Squirtle, Psyduck]
  Fuego:  [Charmander, Vulpix]
  Planta: [Bulbasaur]
  ```

**Código implementado:**
```java
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

        Map<String, List<String>> porTipo = equipo.stream()
                .collect(Collectors.groupingBy(
                        Pokemon::getTipo,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())
                ));

        porTipo.forEach((tipo, lista) -> System.out.println(tipo + ":\t" + lista));
    }
}
```

Captura:

<img width="211" height="60" alt="Captura de pantalla 2026-08-31 144533" src="https://github.com/user-attachments/assets/e9565837-494a-4013-ab24-be7c1d9d29ff" />


Explicación:
La operación terminal `collect()` con `Collectors.groupingBy()` particiona el flujo usando el atributo `tipo` como clave. El colector subordinado `Collectors.mapping()` transforma los elementos agrupados para almacenar exclusivamente los nombres en las listas del mapa resultante.

---

### Ejercicio 14 — Organizar por Región
**Enunciado:**
Agrupar los Pokémon según su región de origen utilizando `groupingBy()`.
- **Datos de entrada:** `Pikachu(Kanto), Chikorita(Johto), Torchic(Hoenn), Piplup(Sinnoh), Charmander(Kanto), Totodile(Johto)`
- **Salida esperada:**
  ```text
  Kanto:  [Pikachu, Charmander]
  Johto:  [Chikorita, Totodile]
  Hoenn:  [Torchic]
  Sinnoh: [Piplup]
  ```

**Código implementado:**
```java
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
```

Captura:

<img width="220" height="75" alt="Captura de pantalla 2026-08-31 144546" src="https://github.com/user-attachments/assets/a5e6335b-b690-4106-b279-90ba2142bfb0" />

Explicación:
`Collectors.groupingBy()` agrupa las instancias tomando `Pokemon::getRegion` como criterio de clasificación. A través de `Collectors.mapping()`, extrae el nombre de cada Pokémon para generar las listas asociadas a cada región

---

### Ejercicio 15 — Maestro de Gimnasios
**Enunciado:**
Dado un listado de entrenadores con sus medallas, encontrar el entrenador con más medallas utilizando `max(Comparator)`.
- **Datos de entrada:** `Ash(8 medallas), Misty(5 medallas), Brock(6 medallas), Gary(10 medallas)`
- **Salida esperada:**
  ```text
  Campeón de gimnasios: Gary
  Medallas obtenidas: 10
  ```

**Código implementado:**
```java
package dosw.semana2.pokemon;

import java.util.Comparator;
import java.util.List;

public class Ejercicio15 {
    public static void main(String[] args) {
        List<Entrenador> entrenadores = List.of(
                new Entrenador(1L, "Ash", 8, List.of()),
                new Entrenador(2L, "Misty", 5, List.of()),
                new Entrenador(3L, "Brock", 6, List.of()),
                new Entrenador(4L, "Gary", 10, List.of())
        );

        Entrenador campeon = entrenadores.stream()
                .max(Comparator.comparingInt(Entrenador::getMedallas))
                .get();

        System.out.println("Campeón de gimnasios: " + campeon.getNombre());
        System.out.println("Medallas obtenidas: " + campeon.getMedallas());
    }
}
```

Captura:

<img width="208" height="40" alt="Captura de pantalla 2026-08-31 144821" src="https://github.com/user-attachments/assets/4fcf884f-559b-4ed7-befd-2df518f0237f" />


Explicación:
La operación terminal `max()` toma un comparador numérico generado mediante `Comparator.comparingInt(Entrenador::getMedallas)` para evaluar cuál entidad posee el mayor número de medallas. Con `.get()` se extrae directamente el objeto `Entrenador` correspondiente al máximo.

---

### Ejercicio 16 — Entrenadores Experimentados
**Enunciado:**
Mostrar únicamente los entrenadores que posean más de 5 medallas utilizando `filter()`.
- **Datos de entrada:** `Ash(8), Misty(5), Brock(6), Gary(10), May(3), Dawn(7)`
- **Salida esperada:**
  ```text
  Entrenadores con > 5 medallas:
  [Ash(8), Brock(6), Gary(10), Dawn(7)]
  ```

**Código implementado:**
```java
package dosw.semana2.pokemon;

import java.util.List;

public class Ejercicio16 {
    public static void main(String[] args) {
        List<Entrenador> entrenadores = List.of(
                new Entrenador(1L, "Ash", 8, List.of()),
                new Entrenador(2L, "Misty", 5, List.of()),
                new Entrenador(3L, "Brock", 6, List.of()),
                new Entrenador(4L, "Gary", 10, List.of()),
                new Entrenador(5L, "May", 3, List.of()),
                new Entrenador(6L, "Dawn", 7, List.of())
        );

        List<String> experimentados = entrenadores.stream()
                .filter(e -> e.getMedallas() > 5)
                .map(e -> e.getNombre() + "(" + e.getMedallas() + ")")
                .toList();

        System.out.println("Entrenadores con > 5 medallas:");
        System.out.println(experimentados);
    }
}
```

Captura:

<img width="279" height="48" alt="Captura de pantalla 2026-08-31 144825" src="https://github.com/user-attachments/assets/cca611cb-8ebe-4963-84df-f7155b76a159" />


Explicación:
La operación intermedia `filter()` evalúa el predicado `e.getMedallas() > 5` descartando a los entrenadores con 5 o menos medallas. Luego, `map()` formatea la representación textual requerida y `.toList()` recolecta los elementos en una lista inmutable.

---

### Ejercicio 17 — Equipo Más Poderoso
**Enunciado:**
Calcular cuál entrenador tiene la suma total de poderCombate más alta entre todos sus Pokémon utilizando `mapToDouble() + sum()`.
- **Datos de entrada:**
  ```text
  Ash:   equipo con PC total 1850
  Gary:  equipo con PC total 2340
  Brock: equipo con PC total 1670
  ```
- **Salida esperada:**
  ```text
  Entrenador más poderoso: Gary
  Poder acumulado del equipo: 2340
  ```

**Código implementado:**
```java
package dosw.semana2.pokemon;

import java.util.Comparator;
import java.util.List;

public class Ejercicio17 {
    public static void main(String[] args) {
        List<Pokemon> equipoAsh = List.of(
                new Pokemon(1L, "Pikachu", "Eléctrico", 50, 650.0, "Kanto", false),
                new Pokemon(2L, "Charizard", "Fuego", 55, 700.0, "Kanto", false),
                new Pokemon(3L, "Sceptile", "Planta", 45, 500.0, "Hoenn", false)
        );

        List<Pokemon> equipoGary = List.of(
                new Pokemon(4L, "Blastoise", "Agua", 60, 800.0, "Kanto", false),
                new Pokemon(5L, "Arcanine", "Fuego", 58, 780.0, "Kanto", false),
                new Pokemon(6L, "Umbreon", "Siniestro", 56, 760.0, "Johto", false)
        );

        List<Pokemon> equipoBrock = List.of(
                new Pokemon(7L, "Onix", "Roca", 40, 550.0, "Kanto", false),
                new Pokemon(8L, "Geodude", "Roca", 38, 520.0, "Kanto", false),
                new Pokemon(9L, "Steelix", "Acero", 48, 600.0, "Johto", false)
        );

        List<Entrenador> entrenadores = List.of(
                new Entrenador(1L, "Ash", 8, equipoAsh),
                new Entrenador(2L, "Gary", 10, equipoGary),
                new Entrenador(3L, "Brock", 6, equipoBrock)
        );

        Entrenador masPoderoso = entrenadores.stream()
                .max(Comparator.comparingDouble(e -> e.getEquipo().stream()
                        .mapToDouble(Pokemon::getPoderCombate)
                        .sum()))
                .get();

        double poderTotal = masPoderoso.getEquipo().stream()
                .mapToDouble(Pokemon::getPoderCombate)
                .sum();

        System.out.println("Entrenador más poderoso: " + masPoderoso.getNombre());
        System.out.println("Poder acumulado del equipo: " + (int) poderTotal);
    }
}
```

Captura:

<img width="240" height="42" alt="Captura de pantalla 2026-08-31 144829" src="https://github.com/user-attachments/assets/51b46827-b3a3-4957-b8b5-85faf9db4ae6" />

---

Explicación:
Se procesa el equipo de cada entrenador mediante un sub-stream con `mapToDouble(Pokemon::getPoderCombate).sum()`, acumulando la suma de poder de combate. Luego, `max()` evalúa la comparación entre entrenadores para obtener el líder con mayor potencia total.


### Ejercicio 18 — Top 5 Pokémon Más Fuertes
**Enunciado:**
Generar un ranking de los cinco Pokémon con mayor poderCombate de toda la Pokédex utilizando `sorted() + limit(5)`.
- **Datos de entrada:** `Lista completa de Pokémon con PC`
- **Salida esperada:**
  ```text
  #1 Mewtwo     – PC: 680
  #2 Charizard  – PC: 610
  #3 Dragonite  – PC: 530
  #4 Gengar     – PC: 495
  #5 Pikachu    – PC: 320
  ```

**Código implementado:**
```java
package dosw.semana2.pokemon;

import java.util.Comparator;
import java.util.List;

public class Ejercicio18 {
    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
                new Pokemon(1L, "Pikachu", "Eléctrico", 25, 320.0, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psíquico", 70, 680.0, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragón", 55, 530.0, "Kanto", false),
                new Pokemon(4L, "Squirtle", "Agua", 15, 210.0, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 45, 495.0, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 60, 610.0, "Kanto", false)
        );

        List<Pokemon> top5 = pokedex.stream()
                .sorted(Comparator.comparingDouble(Pokemon::getPoderCombate).reversed())
                .limit(5)
                .toList();

        int puesto = 1;
        for (Pokemon p : top5) {
            System.out.println("#" + puesto + " " + p.getNombre() + " - PC: " + (int) p.getPoderCombate());
            puesto++;
        }
    }
}
```

Captura:

<img width="166" height="86" alt="Captura de pantalla 2026-08-31 150042" src="https://github.com/user-attachments/assets/626d5037-876e-44ec-bb35-cf5ca405a80f" />


Explicación:
La operación intermedia `sorted()` ordena el stream de forma descendente aplicando `Comparator.comparingDouble(Pokemon::getPoderCombate).reversed()`. La operación intermedia `limit(5)` trunca el flujo quedándose únicamente con los primeros 5 elementos de mayor potencia antes de recolectarlos con `.toList()`.

---

### Ejercicio 19 — Top 3 Entrenadores
**Enunciado:**
Generar un ranking de los 3 mejores entrenadores considerando: 1° más medallas, 2° mayor poder acumulado, 3° orden alfabético como criterio de desempate.
- **Datos de entrada:**
  ```text
  Gary(10 medallas, PC:2340)
  Ash(8 medallas, PC:1850)
  Dawn(7 medallas, PC:2100)
  Brock(6 medallas, PC:1670)
  ```
- **Salida esperada:**
  ```text
  #1 Gary  – 10 medallas, PC: 2340
  #2 Ash   – 8 medallas,  PC: 1850
  #3 Dawn  – 7 medallas,  PC: 2100
  ```

**Código implementado:**
```java
package dosw.semana2.pokemon;

import java.util.Comparator;
import java.util.List;

public class Ejercicio19 {
    public static void main(String[] args) {
        List<Entrenador> entrenadores = List.of(
                new Entrenador(1L, "Gary", 10, List.of(new Pokemon(1L, "Blastoise", "Agua", 60, 2340.0, "Kanto", false))),
                new Entrenador(2L, "Ash", 8, List.of(new Pokemon(2L, "Pikachu", "Eléctrico", 50, 1850.0, "Kanto", false))),
                new Entrenador(3L, "Dawn", 7, List.of(new Pokemon(3L, "Piplup", "Agua", 55, 2100.0, "Sinnoh", false))),
                new Entrenador(4L, "Brock", 6, List.of(new Pokemon(4L, "Onix", "Roca", 45, 1670.0, "Kanto", false)))
        );

        List<Entrenador> top3 = entrenadores.stream()
                .sorted(Comparator
                        .comparingInt(Entrenador::getMedallas).reversed()
                        .thenComparing(Comparator.comparingDouble((Entrenador e) -> e.getEquipo().stream()
                                .mapToDouble(Pokemon::getPoderCombate)
                                .sum()).reversed())
                        .thenComparing(Entrenador::getNombre))
                .limit(3)
                .toList();

        int puesto = 1;
        for (Entrenador e : top3) {
            double pcTotal = e.getEquipo().stream().mapToDouble(Pokemon::getPoderCombate).sum();
            System.out.println("#" + puesto + " " + e.getNombre() + " - " + e.getMedallas() + " medallas, PC: " + (int) pcTotal);
            puesto++;
        }
    }
}
```

Captura:

<img width="243" height="57" alt="Captura de pantalla 2026-08-31 150047" src="https://github.com/user-attachments/assets/24f4d0ca-7e6d-499b-a0db-c4cb707a5aa6" />


Explicación:
Se encadenan múltiples criterios de ordenamiento con `thenComparing()`: primero por medallas descendentes, segundo por la suma del poder de combate de su equipo (mediante un sub-stream con `mapToDouble().sum()`) y tercero por nombre alfabético. Finalmente, `limit(3)` extrae el podio de los 3 mejores.

---

### Ejercicio 20 — Pokédex Analítica
**Enunciado:**
Construir una estructura analítica que muestre: cantidad de Pokémon por tipo, por región, cantidad de legendarios, promedio de nivel y el Pokémon más fuerte utilizando únicamente Streams.
- **Datos de entrada:** `Lista completa de Pokémon con todos sus atributos`
- **Salida esperada:**
  ```text
  Por tipo:     {Fuego=1, Eléctrico=1, Agua=1, Psíquico=2, Fantasma=1, Dragón=1}
  Por región:   {Kanto=7}
  Legendarios:  2
  Promedio niv: 49.3
  Más fuerte:   Mewtwo (PC: 680)
  ```

**Código implementado:**
```java
package dosw.semana2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ejercicio20 {
    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
                new Pokemon(1L, "Pikachu", "Eléctrico", 25, 320.0, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psíquico", 70, 680.0, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragón", 55, 530.0, "Kanto", false),
                new Pokemon(4L, "Squirtle", "Agua", 15, 210.0, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 45, 495.0, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 60, 610.0, "Kanto", false),
                new Pokemon(7L, "Mew", "Psíquico", 75, 650.0, "Kanto", true)
        );

        Map<String, Long> porTipo = pokedex.stream()
                .collect(Collectors.groupingBy(Pokemon::getTipo, Collectors.counting()));

        Map<String, Long> porRegion = pokedex.stream()
                .collect(Collectors.groupingBy(Pokemon::getRegion, Collectors.counting()));

        long legendarios = pokedex.stream()
                .filter(Pokemon::isLegendario)
                .count();

        double promedioNivel = pokedex.stream()
                .mapToInt(Pokemon::getNivel)
                .average()
                .orElse(0.0);

        Pokemon masFuerte = pokedex.stream()
                .max(Comparator.comparingDouble(Pokemon::getPoderCombate))
                .get();

        System.out.println("Por tipo:     " + porTipo);
        System.out.println("Por región:   " + porRegion);
        System.out.println("Legendarios:  " + legendarios);
        System.out.printf("Promedio niv: %.1f\n", promedioNivel);
        System.out.println("Más fuerte:   " + masFuerte.getNombre() + " (PC: " + (int) masFuerte.getPoderCombate() + ")");
    }
}
```

Captura:

<img width="567" height="89" alt="Captura de pantalla 2026-08-31 150053" src="https://github.com/user-attachments/assets/2ceab9ce-5c46-4791-92b6-912d9a9b86bc" />


Explicación:
Se procesan métricas analíticas sobre el catálogo completo: agrupaciones y frecuencias con `Collectors.groupingBy()` y `Collectors.counting()`, conteo con `filter().count()`, cálculo de media aritmética con `mapToInt().average()` y obtención del valor máximo con `max(Comparator)`.

---

## Retos Especiales
#Reto Legendario — Method References

Se uso Method References en ejercicios como:
1. Ejercicio 1 - Pokémon Tipo Fuego
2. Ejercicio 2 - Pokédex Gritona
3. Ejercicio 3 - Poder Total del Equipo
4. Ejercicio 10 - Pokédex Compacta
5. Reto Mewtwo

---
# Reto Shiny — Buenas prácticas de commits: Comprobable en github

---
# Reto Mewtwo — Ejercicio propuesto:
## Torneo Supremo
**Enunciado:**
Ejercicio para clasificar a los Pokémon en un Torneo Supremo, integrando en la solución las 5 operaciones fundamentales: `filter()`, `map()`, `sorted()`, `groupingBy()` y `reduce()`.
  1. Filtrar los Pokémon aptos con `nivel >= 25` (`filter`).
  2. Ordenar a los clasificados por `poderCombate` de mayor a menor (`sorted`).
  3. Agrupar a los participantes según su `region` de origen (`groupingBy`).
  4. Extraer el poder de combate numérico (`map`).
  5. Acumular el poder de combate total del torneo (`reduce`).

**Código implementado:**
```java
package dosw.semana2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RetoMewtwo {

    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
                new Pokemon(1L, "Pikachu", "Eléctrico", 25, 320.0, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psíquico", 70, 680.0, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragón", 55, 530.0, "Kanto", false),
                new Pokemon(4L, "Caterpie", "Bicho", 10, 95.0, "Kanto", false),
                new Pokemon(5L, "Tyranitar", "Roca", 60, 600.0, "Johto", false),
                new Pokemon(6L, "Totodile", "Agua", 15, 180.0, "Johto", false),
                new Pokemon(7L, "Rayquaza", "Dragón", 75, 710.0, "Hoenn", true),
                new Pokemon(8L, "Torchic", "Fuego", 12, 160.0, "Hoenn", false),
                new Pokemon(9L, "Lucario", "Lucha", 50, 520.0, "Sinnoh", false)
        );

        //filter(), sorted() y groupingBy()
        Map<String, List<String>> clasificadosPorRegion = pokedex.stream()
                .filter(p -> p.getNivel() >= 25)
                .sorted(Comparator.comparingDouble(Pokemon::getPoderCombate).reversed())
                .collect(Collectors.groupingBy(
                        Pokemon::getRegion,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())
                ));

        //map() y reduce()
        double poderTotalTorneo = pokedex.stream()
                .filter(p -> p.getNivel() >= 25)
                .map(Pokemon::getPoderCombate)
                .reduce(0.0, Double::sum);

        System.out.println("CLASIFICADOS AL TORNEO SUPREMO (POR REGIÓN)");
        clasificadosPorRegion.forEach((region, pokemones) -> 
                System.out.println(region + ":\t" + pokemones));
        System.out.println("Poder total acumulado de clasificados: " + (int) poderTotalTorneo);
    }
}
```

Captura:

<img width="324" height="105" alt="Captura de pantalla 2026-08-31 151202" src="https://github.com/user-attachments/assets/afb976dd-9e8d-41ad-b7d9-ad57bc6d5849" />


Explicación:
- `filter(p -> p.getNivel() >= 25)`: Elimina del flujo los Pokémon en etapa inicial que no cumplen el umbral competitivo.
- `sorted(Comparator.comparingDouble(...).reversed())`: Ordena el flujo de mayor a menor potencia.
- `groupingBy(Pokemon::getRegion, ...)`: Clasifica el flujo en un mapa indexado por región geográfica, utilizando Method References.
- `map(Pokemon::getPoderCombate)`: Realiza una proyección desacoplada extrayendo únicamente el valor escalar de combate.
- `reduce(0.0, Double::sum)`: Operación terminal asociativa que acumula y totaliza el poder global con el elemento neutro `0.0`.

