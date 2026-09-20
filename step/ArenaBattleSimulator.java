/**
 * Problem 4: Arena Battle Simulator
 * Week 7 - Category A Assignment
 */
interface Attackable {
    String attack();
    String attack(String weaponName); // compile-time overload
}

interface Defendable {
    String defend();
}

abstract class GameCharacter {
    public final String characterId;
    private static int counter = 0;

    public GameCharacter() {
        counter++;
        this.characterId = "CHAR-" + (1000 + counter);
    }

    public String getCharacterId() {
        return characterId;
    }

    public abstract String getSpecialMove();
}

class Warrior extends GameCharacter implements Attackable, Defendable {
    private String name;

    public Warrior(String name) {
        super();
        this.name = name;
    }

    @Override
    public String attack() {
        return name + " strikes with a blade";
    }

    @Override
    public String attack(String weaponName) {
        return name + " strikes with an " + weaponName;
    }

    @Override
    public String defend() {
        return name + " raises a shield";
    }

    @Override
    public String getSpecialMove() {
        return name + " unleashes Whirlwind Slash";
    }
}

// Trap has NO relationship to GameCharacter — only Defendable
class Trap implements Defendable {
    private String trapType;

    public Trap(String trapType) {
        this.trapType = trapType;
    }

    @Override
    public String defend() {
        return trapType + " triggers automatically";
    }
}

public class ArenaBattleSimulator {
    static void resolveDefense(Defendable[] combatants) {
        for (Defendable d : combatants) {
            if (d != null) {
                System.out.println(d.defend());
            }
        }
    }

    public static void main(String[] args) {
        Warrior w = new Warrior("Kael");
        System.out.println(w.attack());
        System.out.println(w.attack("Iron Sword"));
        System.out.println(w.defend());
        System.out.println(w.getSpecialMove());

        Trap t = new Trap("Spike Pit");
        System.out.println(t.defend());

        resolveDefense(new Defendable[]{w, t});
    }
}
