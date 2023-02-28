package domain;

public class Player {
    private final PlayerName playerName;
    private final Cards cards;

    public Player(PlayerName playerName, Cards cards) {
        this.playerName = playerName;
        this.cards = cards;
    }
}
