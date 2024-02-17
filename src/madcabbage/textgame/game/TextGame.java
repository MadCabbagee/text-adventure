package madcabbage.textgame.game;

import madcabbage.textgame.entities.enemy.Enemy;
import madcabbage.textgame.entities.EntityFactory;
import madcabbage.textgame.entities.player.Player;
import madcabbage.textgame.room.Room;
import madcabbage.textgame.room.RoomFactory;
import madcabbage.textgame.utils.Console;

import java.io.IOException;
import java.rmi.server.UID;
import java.util.Random;

public class TextGame {

    public static final Random RNG = new Random(new UID().hashCode());
    private boolean play = false;

    public void start() throws IOException {

        String playerName = Console.promptSpaced("What is your name player?");

        boolean run = true;
        while (run) {

            Player player = EntityFactory.createPlayer(playerName);
            player.welcome();
            Console.printSpace();

            if (!player.askIsReady()) {
                Console.printSpace();
                player.handleNotReady();
                Console.printSpace();
            } else {
                Console.printSpace();
            }

            run = runLoop(player);
            if (run) {
                // Space new game out from last game
                System.out.println("\n\n\n\n\n\n\n\n\n\n");
            }
        }

        Console.print("Thanks for playing!");

        Console.close();
    }

    private boolean runLoop(Player player) {
        play = true;

        while(play) {
            // create room
            Room room = RoomFactory.createRoom();
            Console.print(room.getDescription());

            promptContinue();

            // create enemy
            Enemy enemy = EntityFactory.createEnemy(player.getLevel());
            enemy.announce();

            // do battle
            handleBattle(player, enemy);

            if (player.getHealth() > 0 && player.getLevel() >= 5) {
                handleWin();

            } else if (player.getHealth() <= 0) {
                // player loses game over
                doGameOver();
            }

            Console.printSpaced("__________________________________________________");
        }

        String playAgain = Console.promptWithChoicesSpaced("Would you like to play again?", new String[] { "Yes", "No"});
        return playAgain.equalsIgnoreCase("yes");
    }

    private void promptContinue() {
        Console.prompt("Press enter to continue...");
    }

    public void handleBattle(Player player, Enemy enemy) {
        // higher agility goes first
        // roll change of dodging using agility
        boolean playerTurn = isPlayerTurn(player, enemy);

        boolean fighting = true;
        while (fighting) {
            if (playerTurn) {
                doPlayerTurn(player, enemy);
                playerTurn = false;

                promptContinue();
            } else {
                doEnemyTurn(player, enemy);
                playerTurn = true;
            }

            if (enemy.getHealth() < 1) {
                // player wins
                player.giveXp();
                fighting = false;
            } else if (player.getHealth() < 1) {
                fighting = false;
            }

        }
    }

    private boolean isPlayerTurn(Player player, Enemy enemy) {
        return player.getAgility() > enemy.getAgility();
    }

    private void doPlayerTurn(Player player, Enemy enemy) {
        Console.prompt("Press enter to attack.");
        Console.print("Player turn:");
        String enemyName = enemy.getName();
        Console.print("\tYou swing at the " + enemyName);
        double damage = player.getAttack();
        enemy.takeDamage(damage);
        Console.print("\tYou deal " + damage + " damage to " + enemyName);
        printEnemyHealth(enemy);
    }

    private void printEnemyHealth(Enemy enemy) {
        if (enemy.getHealth() <= 0) {
            Console.print("\tCausing the enemy to die!");
        } else {
            Console.print("\tLeaving its health at " + enemy.getHealth() + " hp.");
        }
    }

    private void doEnemyTurn(Player player, Enemy enemy) {
        Console.print("Enemy turn:");
        Console.print("\tThe enemy attacks!");
        double damage = enemy.getAttack();
        player.takeDamage(damage);
        Console.print("\tYou take " + damage + " damage from " + enemy.getName());
        printPlayerHealth(player);
    }

    private void printPlayerHealth(Player player) {
        if (player.getHealth() <= 0) {
            Console.print("\tCausing you to die!");
        } else {
            Console.print("\tYour health is now " + player.getHealth() + " hp.");
        }
    }

    private void doGameOver() {
        play = false;
        Console.printSpaced("Game over.");
    }

    private void handleWin() {
        Console.print("Congratulations! You won the game!");
        play = false;
    }
}
