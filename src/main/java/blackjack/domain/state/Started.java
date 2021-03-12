package blackjack.domain.state;

import blackjack.domain.card.Hand;

public abstract class Started implements State {
    protected final Hand cards;

    protected Started(final Hand cards) {
        this.cards = cards;
    }

    @Override
    public Hand cards() {
        return cards;
    }

    @Override
    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    @Override
    public boolean isBust() {
        return cards.isBust();
    }

    @Override
    public int score() {
        return cards.score();
    }
}
