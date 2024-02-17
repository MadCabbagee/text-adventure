package madcabbage.textgame.entities.player;

import madcabbage.textgame.utils.Console;
import madcabbage.textgame.game.TextGame;
import madcabbage.textgame.entities.Entity;

public class Player extends Entity {

    private int maxHealth;
    private int xp = 0;
    private static final int baseXp = 50;
    private static final double xpModifier = 1.25f;
    public Player(String name, int level, int health, double attack, int strength, int agility, int intelligence) {
        super(name, level, health, attack, strength, agility, intelligence);
        maxHealth = health;
    }

    public void welcome() {
        Console.print("Welcome to textgame " + name + ". These are your stats: ");
        Console.print(this.toString());
    }

    public boolean askIsReady() {
        String answer = Console.promptWithChoices("Are you ready?", new String[] {"Yes", "No"});
        return answer.equalsIgnoreCase("yes");
    }

    public void handleNotReady() {
        Console.print("A true hero is always ready.");
    }

    @Override public String toString() {
        return "Player Stats:" +
                "\n\tLevel: " + level +
                "\n\tHealth: " + health +
                "\n\tAttack: " + attack +
                "\n\tStrength: " + strength +
                "\n\tAgility: " + agility +
                "\n\tIntelligence: " + intelligence;
    }

    public void giveXp() {
        this.xp += TextGame.RNG.nextInt(/*50,*/ 500);
        checkAndLevelUp();
    }

    private void checkAndLevelUp() {
        if (canLevelUp()) {
            levelUp();
        }
    }

    private boolean canLevelUp() {
        return xp >= (baseXp * level + 1) + (xpModifier * level);
    }

    private void levelUp() {
        level++;
        health = maxHealth;
        attack *= 1.25d;
        Console.printSpaced("You leveled up! These are your new " + this);
    }
}
