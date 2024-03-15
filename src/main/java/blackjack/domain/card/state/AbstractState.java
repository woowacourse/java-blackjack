package blackjack.domain.card.state;

import blackjack.domain.card.Cards;

public abstract class AbstractState implements State {
    private final Cards cards;

    protected AbstractState(final Cards cards) {
        this.cards = cards;
    }

    public Cards cards() {
        return this.cards;
    }

    @Override
    public int calculate() {
        return cards.calculate();
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isHit() {
        return false;
    }

    @Override
    public boolean isStand() {
        return false;
    }
}
