package madcabbage.textgame;

import madcabbage.textgame.game.TextGame;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static final Path Terrain_File = Paths.get("terrain_descriptions.txt");
    public static final Path Sense_File = Paths.get("sense_descriptions.txt");
    public static final Path Directions_File = Paths.get("distance_descriptions.txt");
    public static final Path Enemy_Types_File = Paths.get("enemy_types.txt");

    public static void main(String[] args) throws IOException {
        TextGame game = new TextGame();
        game.start();

    }
}