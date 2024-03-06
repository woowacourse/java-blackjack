package domain;

public class Player implements Participant {
    private final PlayerName playerName;
    private final Hand hand;

    Player(final PlayerName playerName, Hand hand) {
        this.playerName = playerName;
        this.hand = hand;
    }

    public static Player of(String playerName) {
        return new Player(new PlayerName(playerName), Hand.init());
    }

    @Override
    public boolean isDrawable() {
        return !hand.isBurst();
    }

    @Override
    public void draw(final Deck deck) {
        hand.addCard(deck.drawn());
    }

    public HandStatus getPlayerHandStatus() {
        return hand.getHandStatus();
    }
}
