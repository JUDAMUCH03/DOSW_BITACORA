package dosw.semana4.PatronesCombinados;

// Contexto Documento
class Document {
    private final String title;
    private final double amount;
    private DocumentState state;

    public Document(String title, double amount) {
        this.title = title;
        this.amount = amount;
        this.state = new InReviewState();
    }

    public void setState(DocumentState state) {
        this.state = state;
    }

    public DocumentState getState() {
        return state;
    }

    public String getTitle() { return title; }
    public double getAmount() { return amount; }

    public void approve() {
        state.approve(this);
    }

    public void reject() {
        state.reject(this);
    }
}

// State: Comportamiento según el estado del documento
interface DocumentState {
    void approve(Document doc);
    void reject(Document doc);
}

class InReviewState implements DocumentState {
    @Override
    public void approve(Document doc) {
        System.out.println("[State] Transición de estado: IN_REVIEW -> APPROVED");
        doc.setState(new ApprovedState());
    }

    @Override
    public void reject(Document doc) {
        System.out.println("[State] Transición de estado: IN_REVIEW -> REJECTED");
        doc.setState(new RejectedState());
    }
}

class ApprovedState implements DocumentState {
    @Override
    public void approve(Document doc) {
        System.out.println("[State] El documento ya está formalmente aprobado.");
    }

    @Override
    public void reject(Document doc) {
        System.out.println("[State] Operación inválida: Un documento aprobado no puede rechazarse.");
    }
}

class RejectedState implements DocumentState {
    @Override
    public void approve(Document doc) {
        System.out.println("[State] Operación inválida: El documento está rechazado.");
    }

    @Override
    public void reject(Document doc) {
        System.out.println("[State] El documento ya se encuentra rechazado.");
    }
}

// Chain of Responsibility: Cadena de validadores
abstract class DocumentHandler {
    protected DocumentHandler next;

    public DocumentHandler setNext(DocumentHandler next) {
        this.next = next;
        return next;
    }

    public void handle(Document doc) {
        if (canHandle(doc)) {
            process(doc);
        } else if (next != null) {
            next.handle(doc);
        }
    }

    abstract boolean canHandle(Document doc);
    abstract void process(Document doc);
}

class AutorHandler extends DocumentHandler {
    @Override
    boolean canHandle(Document doc) {
        return doc.getAmount() <= 1000;
    }

    @Override
    void process(Document doc) {
        System.out.println("[Handler: Autor] Documento menor a $1000 aprobado por el Autor.");
        doc.approve();
    }
}

class LiderHandler extends DocumentHandler {
    @Override
    boolean canHandle(Document doc) {
        return doc.getAmount() <= 10000;
    }

    @Override
    void process(Document doc) {
        System.out.println("[Handler: Líder] Monto revisado y aprobado por Líder Técnico.");
        doc.approve();
    }
}

class JuridicoHandler extends DocumentHandler {
    @Override
    boolean canHandle(Document doc) {
        return doc.getAmount() <= 50000;
    }

    @Override
    void process(Document doc) {
        System.out.println("[Handler: Jurídico] Contrato de alto valor validado por Jurídico.");
        doc.approve();
    }
}

public class Ejercicio07 {
    public static void main(String[] args) {
        DocumentHandler chain = new AutorHandler();
        chain.setNext(new LiderHandler())
             .setNext(new JuridicoHandler());

        System.out.println("=== CASO 1: APROBACIÓN POR LÍDER ===");
        Document doc1 = new Document("Compra Licencias", 4500.0);
        chain.handle(doc1);

        System.out.println("\n=== CASO 2: APROBACIÓN POR JURÍDICO ===");
        Document doc2 = new Document("Contrato Cloud", 32000.0);
        chain.handle(doc2);
    }
}