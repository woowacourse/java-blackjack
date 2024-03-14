package domain.state;

import domain.card.Deck;

public abstract class Started implements State {
    private final Deck deck = Deck.makeEmptyDeck();

    @Override
    public boolean isFinished() {
        return false;
    }

    public Deck getCards() {
        return deck;
    }
}
