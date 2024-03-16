package blackjack.model.state;

import blackjack.model.cards.Cards;

public abstract class InProgress implements State {
    private final Cards cards;

    public InProgress(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public int getScore() {
        return cards.getScoreValue();
    }
}
