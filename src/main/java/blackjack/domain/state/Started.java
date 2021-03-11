package blackjack.domain.state;

import blackjack.domain.card.PlayerCards;

public abstract class Started implements State {
    protected final PlayerCards cards;

    protected Started(final PlayerCards cards) {
        this.cards = cards;
    }

    @Override
    public PlayerCards cards() {
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
