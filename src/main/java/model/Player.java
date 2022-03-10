package model;

public class Player extends Participator {

    public Player(PlayerName playerName) {
        super(playerName);
    }

    public Player(String playerName) {
        super(new PlayerName(playerName));
    }
}
