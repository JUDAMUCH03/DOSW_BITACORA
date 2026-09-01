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