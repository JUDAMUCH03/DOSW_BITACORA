package dosw.semana4.PatronesCombinados;

// 1. Strategy: Contrato de ejecución de pago
interface PaymentStrategy {
    void process(double amount);
}

// Implementaciones concretas del algoritmo de pago
class TarjetaStrategy implements PaymentStrategy {
    private final String numeroEnmascarado;

    public TarjetaStrategy(String numeroEnmascarado) {
        this.numeroEnmascarado = numeroEnmascarado;
    }

    @Override
    public void process(double amount) {
        System.out.printf("[TARJETA] Procesando cobro de $%.2f con tarjeta finalizada en %s...\n", amount, numeroEnmascarado);
    }
}

class PseStrategy implements PaymentStrategy {
    private final String banco;

    public PseStrategy(String banco) {
        this.banco = banco;
    }

    @Override
    public void process(double amount) {
        System.out.printf("[PSE] Redirigiendo a pasarela bancaria %s para debitar $%.2f...\n", banco, amount);
    }
}

class NequiStrategy implements PaymentStrategy {
    private final String numeroCelular;

    public NequiStrategy(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    @Override
    public void process(double amount) {
        System.out.printf("[NEQUI] Notificación push enviada al %s. Confirmando debito de $%.2f...\n", numeroCelular, amount);
    }
}

class PaypalStrategy implements PaymentStrategy {
    private final String email;

    public PaypalStrategy(String email) {
        this.email = email;
    }

    @Override
    public void process(double amount) {
        System.out.printf("[PAYPAL] Autorizando transacción express checkout para %s por $%.2f USD...\n", email, amount);
    }
}

class StripeStrategy implements PaymentStrategy {
    private final String tokenTransaccion;

    public StripeStrategy(String tokenTransaccion) {
        this.tokenTransaccion = tokenTransaccion;
    }

    @Override
    public void process(double amount) {
        System.out.printf("[STRIPE] Token de cargo verificado (%s). Procesando $%.2f USD...\n", tokenTransaccion, amount);
    }
}

// 2. Factory Method: Contrato para instanciar la estrategia según región
interface PaymentFactory {
    PaymentStrategy create(String type);
}

// Fábrica concreta para Colombia[cite: 1]
class ColombiaPaymentFactory implements PaymentFactory {
    @Override
    public PaymentStrategy create(String type) {
        return switch (type.toUpperCase()) {
            case "PSE" -> new PseStrategy("Bancolombia");
            case "NEQUI" -> new NequiStrategy("3001234567");
            case "TARJETA" -> new TarjetaStrategy("**** 4589");
            default -> throw new IllegalArgumentException("Medio de pago no soportado en Colombia: " + type);
        };
    }
}

// Fábrica concreta para Estados Unidos
class UsaPaymentFactory implements PaymentFactory {
    @Override
    public PaymentStrategy create(String type) {
        return switch (type.toUpperCase()) {
            case "PAYPAL" -> new PaypalStrategy("customer@enterprise.com");
            case "STRIPE" -> new StripeStrategy("tok_1N4x5Y2eZvKYlo2C");
            case "TARJETA" -> new TarjetaStrategy("**** 1122");
            default -> throw new IllegalArgumentException("Medio de pago no soportado en USA: " + type);
        };
    }
}

// Cliente 
class CheckoutService {
    public void executeCheckout(PaymentFactory factory, String paymentType, double amount) {
        // Factory resuelve quién construye; Strategy resuelve cómo ejecutar
        PaymentStrategy strategy = factory.create(paymentType);
        strategy.process(amount);
    }
}

public class Ejercicio01 {
    public static void main(String[] args) {
        CheckoutService checkout = new CheckoutService();

        System.out.println("ESCENARIO 1: CLIENTE EN COLOMBIA");
        PaymentFactory colombiaFactory = new ColombiaPaymentFactory();
        checkout.executeCheckout(colombiaFactory, "NEQUI", 85000.0);
        checkout.executeCheckout(colombiaFactory, "PSE", 240000.0);

        System.out.println("\nESCENARIO 2: CLIENTE EN USA");
        PaymentFactory usaFactory = new UsaPaymentFactory();
        checkout.executeCheckout(usaFactory, "PAYPAL", 150.0);
        checkout.executeCheckout(usaFactory, "STRIPE", 49.99);
    }
}