package madcabbage.textgame.room;

public class Room {

    private final String terrain;
    private final String direction;
    private final String sense;

    public Room(String terrain, String direction, String sense) {
        this.terrain = terrain;
        this.direction = direction;
        this.sense = sense;
    }

    public String getTerrain() {
        return terrain;
    }

    public String getDistance() {
        return direction;
    }

    public String getSense() {
        return sense;
    }

    public String getDescription() {
        return getTerrain() + " " + getDistance() + " " + getSense();
    }
}
