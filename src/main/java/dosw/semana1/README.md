# SEMANA No 1 — DOSW Manejo de Streams

## Datos personales:
- **Nombre y Apellido:** Juan Munar
- **Código de Estudiante:** 1000103253
- **Curso:** DOSW (Desarrollo y Operaciones de Software)

---

### Ejercicio 01 — Números Pares mayores a diez
**Enunciado:**
Dada una lista de números enteros, necesitamos obtener una nueva lista solo con los números pares mayores a 10.
- **Datos de entrada:** `[3, 8, 10, 12, 15, 18, 20]`
- **Salida esperada:** `[12, 18, 20]`

**Código implementado:**
```java
package dosw.semana1.streams;

import java.util.List;

public class TallerEjercicio01 {
    public static void main(String[] args) {
        List<Integer> numeros = List.of(3, 8, 10, 12, 15, 18, 20);

        List<Integer> resultado = numeros.stream().filter(n -> n > 10 && n % 2 == 0).toList();

        System.out.println("Entrada: " + numeros);
        System.out.println("Salida esperada: " + resultado);
    }
}
```

Captura:

<img width="260" height="33" alt="image" src="https://github.com/user-attachments/assets/eac23f06-1eae-4894-8ff4-a24d12fb02da" />


Explicación:
Se crea un flujo declarativo con `.stream()` sobre la colección de origen sin alterar su estado original. La operación intermedia `filter()` evalúa un predicado compuesto que valida simultáneamente que el número sea estrictamente mayor a 10 y que su residuo módulo 2 sea 0. Finalmente, el método terminal `.toList()` recolecta los elementos filtrados en una lista inmutable.

---

### Ejercicio 02 — Cantidad de Palabras con más de 4 caracteres
**Enunciado:**
Dada una lista de palabras, se requiere:
1. Filtrar las palabras que tengan más de 4 caracteres.
2. Convertirlas en Mayúsculas.
3. Ordenarlas alfabéticamente.
4. Obtener la cantidad total de palabras resultantes.
- **Datos de entrada:** `["java", "stream", "api", "functional", "code", "git"]`
- **Salida esperada:** `Cantidad de palabras resultantes: 2`

**Código implementado:**
```java
package dosw.semana1.streams;

import java.util.List;

public class TallerEjercicio02 {
    public static void main(String[] args) {
        List<String> palabras = List.of("java", "stream", "api", "functional", "code", "git");

        long cantidad = palabras.stream().filter(p -> p.length() > 4).map(String::toUpperCase).sorted().count();

        System.out.println("Palabras de entrada: " + palabras);
        System.out.println("Cantidad de palabras resultantes: " + cantidad);
    }
}
```

Captura:

<img width="470" height="37" alt="image" src="https://github.com/user-attachments/assets/b2a0e910-591b-42ef-b765-1b9f6fc83653" />


Explicación:
El pipeline encadena cuatro operaciones funcionales: `filter()` descarta todas las cadenas cuya longitud sea menor o igual a 4; `map(String::toUpperCase)` realiza una proyección inmutable transformando los caracteres a mayúsculas mediante Method Reference; `sorted()` aplica el orden natural lexicográfico; y la operación terminal `count()` evalúa la cardinalidad total de los elementos procesados devolviendo un valor de tipo `long`.

---

### Ejercicio 03 — Obtener nombres de los Usuarios
**Enunciado:**
Dada una lista de usuarios con los atributos: `id`, `name`, `age`, `active`.
Filtra únicamente los usuarios activos, obtén una lista con los nombres en mayúscula y ordenada alfabéticamente.
- **Datos de entrada:** `users = List<User>`
- **Salida esperada:** `sortedUsers = List<String>` (nombres en mayúsculas y ordenados alfabéticamente)

**Código implementado:**
```java
package dosw.semana1.streams;

import java.util.List;

public class TallerEjercicio03 {

    public static class User {
        private Long id;
        private String name;
        private int age;
        private boolean active;

        public User(Long id, String name, int age, boolean active) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.active = active;
        }

        public Long getId() { return id; }
        public String getName() { return name; }
        public int getAge() { return age; }
        public boolean isActive() { return active; }

        public void setId(Long id) { this.id = id; }
        public void setName(String name) { this.name = name; }
        public void setAge(int age) { this.age = age; }
        public void setActive(boolean active) { this.active = active; }
    }

    public static void main(String[] args) {
        List<User> users = List.of(
                new User(1L, "carlos", 22, true),
                new User(2L, "ana", 17, false),
                new User(3L, "beatriz", 25, true),
                new User(4L, "david", 30, false),
                new User(5L, "andres", 19, true)
        );

        List<String> sortedUsers = users.stream().filter(User::isActive).map(User::getName).map(String::toUpperCase).sorted().toList();
        System.out.println("sortedUsers = " + sortedUsers);
    }
}
```

Captura:

<img width="289" height="25" alt="image" src="https://github.com/user-attachments/assets/2bc174d4-7bb4-4b02-9efa-17eea39e3bf3" />


Explicación:
Se procesa una colección de objetos de dominio aplicando `filter(User::isActive)` para conservar únicamente las instancias con estado activo. Posteriormente, se extrae el atributo nominal mediante `map(User::getName)`, se transforma el texto a mayúsculas con `map(String::toUpperCase)` y se ordena ascendentemente con `sorted()` antes de consolidar el resultado en una nueva lista inmutable con `.toList()`.

---

### Ejercicio 04 — Personas mayores de edad
**Enunciado:**
Dado un listado de Usuarios y utilizando los mismos atributos anteriores, filtrar las personas mayores de edad ($\ge 18$ años) y obtener sus nombres.
- **Datos de entrada:** `users = List<User>`
- **Salida esperada:** `Personas mayores de edad: [Juan, Mateo, Lucas]`

**Código implementado:**
```java
package dosw.semana1.streams;

import java.util.List;

public class TallerEjercicio04 {

    public static class User {
        private Long id;
        private String name;
        private int age;
        private boolean active;

        public User(Long id, String name, int age, boolean active) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.active = active;
        }

        public Long getId() { return id; }
        public String getName() { return name; }
        public int getAge() { return age; }
        public boolean isActive() { return active; }

        public void setId(Long id) { this.id = id; }
        public void setName(String name) { this.name = name; }
        public void setAge(int age) { this.age = age; }
        public void setActive(boolean active) { this.active = active; }
    }

    public static void main(String[] args) {
        List<User> users = List.of(
                new User(1L, "Juan", 20, true),
                new User(2L, "Sofia", 16, true),
                new User(3L, "Mateo", 18, false),
                new User(4L, "Valeria", 15, true),
                new User(5L, "Lucas", 24, true)
        );

        List<String> nombresMayoresEdad = users.stream().filter(u -> u.getAge() >= 18).map(User::getName).toList();
        System.out.println("Personas mayores de edad: " + nombresMayoresEdad);
    }
}
```

Captura:

<img width="346" height="21" alt="image" src="https://github.com/user-attachments/assets/b82ae662-7f0b-452a-b0b5-cfab3e20c2c7" />


Explicación:
Se utiliza la operación intermedia `filter()` con una función lambda que evalúa la regla de negocio `u.getAge() >= 18`. Luego, `map(User::getName)` proyecta únicamente la propiedad del nombre de cada usuario aprobado, transformando el tipo de dato del flujo de `Stream<User>` a `Stream<String>` antes de recolectar la lista final.

---

### Ejercicio 05 — Transacciones Bancarias
**Enunciado:**
Dada una lista de transacciones bancarias representadas por objetos:
`class Transaction { String id; double amount; boolean approved; }`
Se requiere procesar la lista usando Streams para:
1. Usar `peek()` para ver cada transacción procesada (`System.out.println`).
2. Verificar si existe al menos una transacción no aprobada.
3. Retornar `true` o `false` indicando si el lote de transacciones es válido.
- **Datos de entrada:** `transacciones = List<Transaction>`
- **Salida esperada:** Visualización del flujo en consola y resultado booleano del lote

**Código implementado:**
```java
package dosw.semana1.streams;

import java.util.List;

public class TallerEjercicio05 {

    public static class Transaction {
        private String id;
        private double amount;
        private boolean approved;

        public Transaction(String id, double amount, boolean approved) {
            this.id = id;
            this.amount = amount;
            this.approved = approved;
        }

        public String getId() { return id; }
        public double getAmount() { return amount; }
        public boolean isApproved() { return approved; }

        public void setId(String id) { this.id = id; }
        public void setAmount(double amount) { this.amount = amount; }
        public void setApproved(boolean approved) { this.approved = approved; }

        @Override
        public String toString() {
            return "Transaction{id='" + id + "', amount=" + amount + ", approved=" + approved + "}";
        }
    }

    public static void main(String[] args) {
        List<Transaction> transacciones = List.of(
                new Transaction("TX-101", 150.50, true),
                new Transaction("TX-102", 320.00, true),
                new Transaction("TX-103", 85.00, false),
                new Transaction("TX-104", 500.00, true)
        );

        boolean existeNoAprobada = transacciones.stream().peek(tx -> System.out.println("Procesando: " + tx)).anyMatch(tx -> !tx.isApproved());

        boolean loteValido = !existeNoAprobada;

        System.out.println("¿Existe al menos una transacción no aprobada?: " + existeNoAprobada);
        System.out.println("¿El lote de transacciones es válido?: " + loteValido);
    }
}
```

Captura:

<img width="481" height="84" alt="image" src="https://github.com/user-attachments/assets/30f0dd09-0ddf-4356-b202-cab411e91ac4" />


Explicación:
`peek()` actúa como una operación intermedia de observación que ejecuta una acción de logging (`System.out.println`) sobre cada elemento sin mutar el flujo ni consumir el stream. La operación terminal `anyMatch()` implementa evaluación en cortocircuito: en el instante en que detecta la primera transacción con `!tx.isApproved()`, interrumpe el procesamiento restante optimizando el rendimiento computacional.


