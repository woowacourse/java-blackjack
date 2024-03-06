package domain;

public class Player {
    private final PlayerName playerName;

    private Player(final PlayerName playerName) {
        this.playerName = playerName;
    }

    public static Player of(String playerName) {
        return new Player(new PlayerName(playerName));
    }
}
