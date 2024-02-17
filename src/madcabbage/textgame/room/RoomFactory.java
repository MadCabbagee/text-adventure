package madcabbage.textgame.room;

import madcabbage.textgame.Main;
import madcabbage.textgame.game.TextGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RoomFactory {
    private static final List<String> Terrain_Descriptions;
    private static final List<String> Distance_Descriptions;
    private static final List<String> Sense_Descriptions;

    static {
        Terrain_Descriptions = readLines(Main.Terrain_File);
        Distance_Descriptions = readLines(Main.Directions_File);
        Sense_Descriptions = readLines(Main.Sense_File);
    }

    private RoomFactory() {
        // hide implicit public constructor
    }

    private static List<String> readLines(Path filePath) {
        BufferedReader br = new BufferedReader(new InputStreamReader(Main.class.getClassLoader().getResourceAsStream(filePath.toString())));
        List<String> lines = new ArrayList<>();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return lines;
    }

    public static Room createRoom() {
        String terrain = Terrain_Descriptions.get(TextGame.RNG.nextInt(/*0,*/ Terrain_Descriptions.size()));
        String distance = Distance_Descriptions.get(TextGame.RNG.nextInt(/*0,*/ Distance_Descriptions.size()));
        String sense = Sense_Descriptions.get(TextGame.RNG.nextInt(/*0,*/ Sense_Descriptions.size()));

        return new Room(terrain, distance, sense);
    }
}
