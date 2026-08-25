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

        List<Integer> resultado = numeros.stream()
                .filter(n -> n > 10 && n % 2 == 0)
                .toList();

        System.out.println("Entrada: " + numeros);
        System.out.println("Salida esperada: " + resultado);
    }
}

Captura:
<img width="254" height="38" alt="image" src="https://github.com/user-attachments/assets/85b8208a-cbf8-4123-b324-085798bb1f77" />

Explicación:
Se crea un flujo declarativo con .stream() sobre la colección de origen sin alterar su estado original. La operación intermedia filter() evalúa un predicado compuesto que valida simultáneamente que el número sea estrictamente mayor a 10 y que su residuo módulo 2 sea 0. Finalmente, el método terminal .toList() recolecta los elementos filtrados en una lista inmutable.

---

### Ejercicio 02 — Nombre del Ejercicio


