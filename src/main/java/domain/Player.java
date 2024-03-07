package domain;

public class Player implements Participant {
    private final PlayerName playerName;
    private final Hand hand;

    Player(final PlayerName playerName, Hand hand) {
        this.playerName = playerName;
        this.hand = hand;
    }

    public static Player of(PlayerName playerName) {
        return new Player(playerName, Hand.init());
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
