package domain.state;

import domain.card.Cards;

public abstract class Started implements State {
    private final Cards cards = Cards.makeEmptyDeck();

    @Override
    public boolean isFinished() {
        return false;
    }

    public Cards getCards() {
        return cards;
    }
}
