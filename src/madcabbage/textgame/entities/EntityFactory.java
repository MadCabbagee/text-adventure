package madcabbage.textgame.entities;

import madcabbage.textgame.Main;
import madcabbage.textgame.game.TextGame;
import madcabbage.textgame.entities.enemy.Enemy;
import madcabbage.textgame.entities.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EntityFactory {
    private static final List<String> Enemy_Types = new ArrayList<>();

    static {
        InputStream enemyTypesInputStream = Main.class.getClassLoader().getResourceAsStream(Main.Enemy_Types_File.toString());
        BufferedReader br = new BufferedReader(new InputStreamReader(enemyTypesInputStream));
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    Enemy_Types.add(line);
                }
                br.close();
            } catch (IOException e) {
                throw new RuntimeException("Issue reading enemies from file.");
            }
    }

    private EntityFactory() {
        // hide implicit public constructor
    }

    public static Enemy createEnemy(int maxLevel) {
        String name = chooseRandomEnemyType();
        int level = TextGame.RNG.nextInt(/*0,*/ maxLevel + 1);
        double health =  (level * Enemy.Base_Health) + (double) Enemy.Base_Health;
        double attack = TextGame.RNG.nextInt(/*1,*/ 11);
        int strength = TextGame.RNG.nextInt(/*1,*/ 11);
        int intelligence = TextGame.RNG.nextInt(/*0,*/ 6);
        int agility = TextGame.RNG.nextInt(/*1,*/ 11);
        int loot = TextGame.RNG.nextInt(/*1,*/ 6);

        return new Enemy(name, level, health, attack, strength, intelligence, agility, loot);
    }

    private static String chooseRandomEnemyType() {
        return Enemy_Types.get(TextGame.RNG.nextInt(/*0,*/ Enemy_Types.size()));
    }

    public static Player createPlayer(String name) {
        int level = 0;
        int health = TextGame.RNG.nextInt(/*50,*/ 101);
        int attack = TextGame.RNG.nextInt(/*5,*/ 11);
        int strength = TextGame.RNG.nextInt(/*5,*/ 11);
        int agility = TextGame.RNG.nextInt(/*5,*/ 11);
        int intelligence = TextGame.RNG.nextInt(/*0,*/ 6);

        return new Player(name, level, health, attack, strength, agility, intelligence);
    }
}
