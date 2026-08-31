package dosw.semana4.PatronesCombinados;

import java.util.ArrayList;
import java.util.List;

// Modelo de Dominio e Inmutabilidad
enum OrderStatus {
    PENDIENTE, ENVIADO, ENTREGADO
}

record OrderEvent(Long orderId, String cliente, String email, String celular, OrderStatus nuevoEstado) {}

// Representación desacoplada del mensaje construido
record MessagePayload(String canal, String contenido) {}

// 1. Factory Method: Fábrica de formateo de mensajes específicos por canal
interface MessageFactory {
    MessagePayload build(OrderEvent event);
}

class EmailMessageFactory implements MessageFactory {
    @Override
    public MessagePayload build(OrderEvent event) {
        String html = "<html><body><h1>Actualización de Pedido #" + event.orderId() + 
                      "</h1><p>Estimado/a " + event.cliente() + ", su pedido está: <b>" + 
                      event.nuevoEstado() + "</b>.</p></body></html>";
        return new MessagePayload("EMAIL", html);
    }
}

class SmsMessageFactory implements MessageFactory {
    @Override
    public MessagePayload build(OrderEvent event) {
        String texto = String.format("Pedido #%d: Hola %s, su estado es %s. (Max 160 chars)",
                event.orderId(), event.cliente(), event.nuevoEstado());
        return new MessagePayload("SMS", texto);
    }
}

class PushMessageFactory implements MessageFactory {
    @Override
    public MessagePayload build(OrderEvent event) {
        String json = "{\"notification\": {\"title\": \"Pedido #" + event.orderId() + 
                      "\", \"body\": \"Nuevo estado: " + event.nuevoEstado() + "\"}}";
        return new MessagePayload("PUSH", json);
    }
}

// 2. Observer: Suscriptores que consumen eventos de pedidos
interface NotificationObserver {
    void notify(OrderEvent event);
}

class EmailNotifier implements NotificationObserver {
    private final MessageFactory messageFactory = new EmailMessageFactory();

    @Override
    public void notify(OrderEvent event) {
        MessagePayload message = messageFactory.build(event);
        System.out.printf("[NOTIFICADOR EMAIL -> %s]: %s\n", event.email(), message.contenido());
    }
}

class SmsNotifier implements NotificationObserver {
    private final MessageFactory messageFactory = new SmsMessageFactory();

    @Override
    public void notify(OrderEvent event) {
        MessagePayload message = messageFactory.build(event);
        System.out.printf("[NOTIFICADOR SMS -> %s]: %s\n", event.celular(), message.contenido());
    }
}

class PushNotifier implements NotificationObserver {
    private final MessageFactory messageFactory = new PushMessageFactory();

    @Override
    public void notify(OrderEvent event) {
        MessagePayload message = messageFactory.build(event);
        System.out.printf("[NOTIFICADOR PUSH -> Token APP]: %s\n", message.contenido());
    }
}

// Subject: Orquestador de pedidos
class OrderSubject {
    private final Long id;
    private final String cliente;
    private final String email;
    private final String celular;
    private OrderStatus estado;
    private final List<NotificationObserver> observers = new ArrayList<>();

    public OrderSubject(Long id, String cliente, String email, String celular) {
        this.id = id;
        this.cliente = cliente;
        this.email = email;
        this.celular = celular;
        this.estado = OrderStatus.PENDIENTE;
    }

    public void attach(NotificationObserver observer) {
        observers.add(observer);
    }

    public void detach(NotificationObserver observer) {
        observers.remove(observer);
    }

    public void setEstado(OrderStatus nuevoEstado) {
        this.estado = nuevoEstado;
        System.out.printf("\n>>> EVENTO DE NEGOCIO: Pedido #%d cambió su estado a [%s] <<<\n", id, nuevoEstado);
        notifyObservers();
    }

    private void notifyObservers() {
        OrderEvent event = new OrderEvent(id, cliente, email, celular, estado);
        for (NotificationObserver observer : observers) {
            observer.notify(event);
        }
    }
}

public class Ejercicio02 {
    public static void main(String[] args) {
        OrderSubject pedido = new OrderSubject(10482L, "Juan Munar", "juan@engineering.com", "+573009876543");

        // Suscribir canales activos
        pedido.attach(new EmailNotifier());
        pedido.attach(new SmsNotifier());
        pedido.attach(new PushNotifier());

        // Disparar transiciones de ciclo de vida
        pedido.setEstado(OrderStatus.ENVIADO);
        pedido.setEstado(OrderStatus.ENTREGADO);
    }
}