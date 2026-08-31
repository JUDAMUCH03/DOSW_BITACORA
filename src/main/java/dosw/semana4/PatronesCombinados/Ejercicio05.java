package dosw.semana4.PatronesCombinados;

// Servicio Legado
class LegacyBankService {
    public void executeTransaction(String accountNumber, int amountInCents) {
        System.out.printf("[LegacyBankService] Transacción ejecutada en cuenta %s por %d centavos.\n",
                accountNumber, amountInCents);
    }
}

// Interfaz Moderna
interface PaymentProcessor {
    void pay(double amount);
}

// Adapter: Traduce la interfaz moderna al servicio legado
class LegacyBankAdapter implements PaymentProcessor {
    private final LegacyBankService legacy;
    private final String accountNumber;

    public LegacyBankAdapter(LegacyBankService legacy, String accountNumber) {
        this.legacy = legacy;
        this.accountNumber = accountNumber;
    }

    @Override
    public void pay(double amount) {
        int cents = (int) (amount * 100); // traducción de formato
        legacy.executeTransaction(accountNumber, cents);
    }
}

// Facade: Simplifica los pasos de inicialización
class BankFacade {
    private final PaymentProcessor adapter;

    public BankFacade(String accountNumber) {
        LegacyBankService legacyService = new LegacyBankService();
        this.adapter = new LegacyBankAdapter(legacyService, accountNumber);
    }

    public void procesarPago(double monto) {
        System.out.println("[BankFacade] Ejecutando verificación de seguridad, apertura de sesión y contexto...");
        adapter.pay(monto);
        System.out.println("[BankFacade] Pago completado y sesión cerrada exitosamente.\n");
    }
}

public class Ejercicio05 {
    public static void main(String[] args) {
        BankFacade facade = new BankFacade("ACC-9988-CORP");
        facade.procesarPago(150.75);
        facade.procesarPago(320.00);
    }
}