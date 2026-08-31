package dosw.semana4.PatronesCombinados;

record Credentials(String username, String token, String type) {}
record AuthResult(boolean success, String userRole) {}

// Strategy: Mecanismo de autenticación
interface AuthStrategy {
    AuthResult authenticate(Credentials c);
}

class PasswordStrategy implements AuthStrategy {
    @Override
    public AuthResult authenticate(Credentials c) {
        System.out.println("[Strategy: Password] Verificando contraseña de " + c.username());
        return new AuthResult(true, "USER");
    }
}

class GoogleStrategy implements AuthStrategy {
    @Override
    public AuthResult authenticate(Credentials c) {
        System.out.println("[Strategy: Google OAuth] Token de Google validado.");
        return new AuthResult(true, "ADMIN");
    }
}

class BiometricStrategy implements AuthStrategy {
    @Override
    public AuthResult authenticate(Credentials c) {
        System.out.println("[Strategy: Biometría] Huella dactilar reconocida.");
        return new AuthResult(true, "SUPERUSER");
    }
}

// Chain of Responsibility: Validaciones post-autenticación
abstract class SecurityValidator {
    protected SecurityValidator next;

    public SecurityValidator setNext(SecurityValidator next) {
        this.next = next;
        return next;
    }

    public void validate(Credentials c, AuthResult auth) {
        if (check(c, auth)) {
            if (next != null) next.validate(c, auth);
            else System.out.println("🔓 Acceso Concedido: Todas las validaciones de seguridad superadas.\n");
        } else {
            System.out.println("❌ Acceso Denegado por fallo en validación de seguridad.\n");
        }
    }

    abstract boolean check(Credentials c, AuthResult auth);
}

class CredentialValidator extends SecurityValidator {
    @Override
    boolean check(Credentials c, AuthResult auth) {
        System.out.println("[Chain: 1/3] Validando estado activo de credenciales...");
        return auth.success();
    }
}

class PermissionValidator extends SecurityValidator {
    @Override
    boolean check(Credentials c, AuthResult auth) {
        System.out.println("[Chain: 2/3] Validando permisos para rol: " + auth.userRole());
        return "ADMIN".equals(auth.userRole()) || "SUPERUSER".equals(auth.userRole());
    }
}

class LocationValidator extends SecurityValidator {
    @Override
    boolean check(Credentials c, AuthResult auth) {
        System.out.println("[Chain: 3/3] Validando IP y ubicación corporativa...");
        return true;
    }
}

public class Ejercicio09 {
    public static void main(String[] args) {
        // Strategy
        AuthStrategy strategy = new GoogleStrategy();
        Credentials creds = new Credentials("juan.munar", "google_token_xyz", "GOOGLE");
        AuthResult result = strategy.authenticate(creds);

        // Chain: validaciones post-autenticación
        CredentialValidator cred = new CredentialValidator();
        PermissionValidator perm = new PermissionValidator();
        LocationValidator loc = new LocationValidator();

        cred.setNext(perm).setNext(loc); // encadenamiento literal del esquema

        cred.validate(creds, result);
    }
}