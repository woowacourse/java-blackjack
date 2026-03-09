package domain;

public class TestBlackjackGame extends BlackjackGame {
    private final Deck fixedDeck;

    public TestBlackjackGame(Deck fixedDeck) {
        this.fixedDeck = fixedDeck;
    }

    @Override
    public void makeDeck() {
        setDeck(fixedDeck);
    }
}