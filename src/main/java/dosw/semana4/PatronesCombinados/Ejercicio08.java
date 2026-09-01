package dosw.semana4.PatronesCombinados;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum Size { SMALL, MEDIUM, LARGE }
enum Meat { BEEF, DOUBLE_BEEF, CHICKEN, VEGGIE }

// Observer
interface OrderObserver {
    void update(Order order);
}

class KitchenService implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("👨‍🍳 [Cocina] Comanda recibida. Preparando orden.");
    }
}

class BillingService implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("💳 [Facturación] Cuenta generada y registrada en el sistema.");
    }
}

class DeliveryService implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("🛵 [Domicilios] Pedido encolado para asignación de ruta.");
    }
}

// Objeto Order Inmutable y Subject
class Order {
    private final Size size;
    private final Meat meat;
    private final List<String> toppings;
    private final List<String> sides;
    private final List<OrderObserver> observers = new ArrayList<>();

    public Order(Size size, Meat meat, List<String> toppings, List<String> sides) {
        this.size = size;
        this.meat = meat;
        this.toppings = List.copyOf(toppings);
        this.sides = List.copyOf(sides);
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void confirm() {
        System.out.println(">>> Pedido Confirmado: " + size + " | " + meat + " | Toppings: " + toppings + " | Acompañamientos: " + sides + " <<<");
        for (OrderObserver obs : observers) {
            obs.update(this);
        }
    }
}

// Builder: Construcción del pedido
class OrderBuilder {
    private Size size = Size.MEDIUM;
    private Meat meat = Meat.BEEF;
    private final List<String> toppings = new ArrayList<>();
    private final List<String> sides = new ArrayList<>();

    public OrderBuilder setSize(Size size) {
        this.size = size;
        return this;
    }

    public OrderBuilder setMeat(Meat meat) {
        this.meat = meat;
        return this;
    }

    public OrderBuilder addTopping(String... toppings) {
        this.toppings.addAll(Arrays.asList(toppings));
        return this;
    }

    public OrderBuilder addSide(String... sides) {
        this.sides.addAll(Arrays.asList(sides));
        return this;
    }

    public Order build() {
        return new Order(size, meat, toppings, sides);
    }
}

public class Ejercicio08 {
    public static void main(String[] args) {
        // Builder
        Order order = new OrderBuilder()
                .setSize(Size.LARGE)
                .setMeat(Meat.DOUBLE_BEEF)
                .addTopping("queso", "lechuga")
                .addSide("papas", "gaseosa")
                .build();

        // Observer
        order.addObserver(new KitchenService());
        order.addObserver(new BillingService());
        order.addObserver(new DeliveryService());

        // Notificación
        order.confirm();
    }
}