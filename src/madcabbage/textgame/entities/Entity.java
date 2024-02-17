package madcabbage.textgame.entities;

public abstract class Entity {

    protected String name;
    protected int level;
    protected double health;
    protected double attack;
    protected int strength;
    protected int agility;
    protected int intelligence;

    public Entity(String name, int level, double health, double attack, int strength, int agility, int intelligence) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.attack = attack;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
    }

    public int getLevel() {
        return level;
    }

    public double getHealth() {
        return health;
    }

    public double getAttack() {
        return attack;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    @Override public String toString() {
        return "Entity Stats:" +
                "\n\tLevel: " + level +
                "\n\tHealth: " + health +
                "\n\tAttack: " + attack +
                "\n\tStrength: " + strength +
                "\n\tAgility: " + agility +
                "\n\tIntelligence: " + intelligence;
    }

    public double takeDamage(double damage) {
        health -= damage;
        return damage;
    }

    public String getName() {
        return name;
    }
}
