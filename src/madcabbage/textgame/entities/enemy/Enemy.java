package madcabbage.textgame.entities.enemy;

import madcabbage.textgame.utils.Console;
import madcabbage.textgame.entities.Entity;

public class Enemy extends Entity {


    public static final int Base_Health = 50;
    private int loot;

    public Enemy(String name, int level, double health, double attack, int strength, int agility, int intelligence, int loot) {
        super(name, level, health, attack, strength, agility, intelligence);
        this.loot = loot;
    }

    public void announce() {
        Console.print("A level " + level + " " + name + " has appeared! What will you do?");
    }

    @Override public String toString() {
        return "Enemy Stats:" +
                "\n\tLevel: " + level +
                "\n\tHealth: " + health +
                "\n\tAttack: " + attack +
                "\n\tStrength: " + strength +
                "\n\tAgility: " + agility +
                "\n\tIntelligence: " + intelligence;
    }
}
