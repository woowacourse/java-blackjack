package domain;

public class Player {
    private final PlayerName playerName;
    private final Hand hand;

    Player(final PlayerName playerName, Hand hand) {
        this.playerName = playerName;
        this.hand = hand;
    }

    public static Player of(String playerName) {
        return new Player(new PlayerName(playerName), Hand.init());
    }

    public boolean isBurst() {
        return hand.isBurst();
    }

    public void draw(final Deck deck) {
        hand.addCard(deck.drawn());
    }
}
