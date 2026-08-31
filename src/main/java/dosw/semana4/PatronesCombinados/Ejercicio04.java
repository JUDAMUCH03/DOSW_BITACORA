package dosw.semana4.PatronesCombinados;

// Componente Base
interface Character {
    String getDescription();
    int getAttackPower();
    void attack();
}

// Personaje Concreto
class BaseWarrior implements Character {
    private final String armor;
    private final String weapon;
    private final String skill;

    public BaseWarrior(String armor, String weapon, String skill) {
        this.armor = armor;
        this.weapon = weapon;
        this.skill = skill;
    }

    @Override
    public String getDescription() {
        return "Guerrero [Armadura: " + armor + ", Arma: " + weapon + ", Habilidad: " + skill + "]";
    }

    @Override
    public int getAttackPower() {
        return 50;
    }

    @Override
    public void attack() {
        System.out.println("Ataque base con " + weapon + " (Daño: " + getAttackPower() + ")");
    }
}

// Builder: Construcción paso a paso al inicio
class WarriorBuilder {
    private String armor = "cuero";
    private String weapon = "espada corta";
    private String skill = "golpe";

    public WarriorBuilder setArmor(String armor) {
        this.armor = armor;
        return this;
    }

    public WarriorBuilder setWeapon(String weapon) {
        this.weapon = weapon;
        return this;
    }

    public WarriorBuilder setSkill(String skill) {
        this.skill = skill;
        return this;
    }

    public Character build() {
        return new BaseWarrior(armor, weapon, skill);
    }
}

// Decorator Base
abstract class CharacterDecorator implements Character {
    protected final Character wrapped;

    public CharacterDecorator(Character wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription();
    }

    @Override
    public int getAttackPower() {
        return wrapped.getAttackPower();
    }

    @Override
    public void attack() {
        wrapped.attack();
    }
}

// Decoradores Concretos
class ShieldDecorator extends CharacterDecorator {
    public ShieldDecorator(Character character) {
        super(character);
    }

    @Override
    public void attack() {
        System.out.print("[Escudo de Hielo Activo] ");
        super.attack();
    }
}

class SpeedDecorator extends CharacterDecorator {
    public SpeedDecorator(Character character) {
        super(character);
    }

    @Override
    public int getAttackPower() {
        return super.getAttackPower() + 20;
    }

    @Override
    public void attack() {
        System.out.print("[Velocidad Extra] ");
        super.attack();
    }
}

public class Ejercicio04 {
    public static void main(String[] args) {
        // Builder: construcción al inicio
        WarriorBuilder builder = new WarriorBuilder();
        Character warrior = builder.setArmor("steel")
                                   .setWeapon("sword")
                                   .setSkill("rage")
                                   .build();

        System.out.println("Personaje Base: " + warrior.getDescription());

        // Decorator: poderes durante la partida
        Character powered = new ShieldDecorator(
                              new SpeedDecorator(warrior));

        powered.attack(); // ejecuta: escudo + velocidad extra + ataque base
    }
}