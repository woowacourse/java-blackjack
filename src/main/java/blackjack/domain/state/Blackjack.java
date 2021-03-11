package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Blackjack extends Finished {
    public Blackjack(final Cards cards) {
        super(cards);
    }

    @Override
    public double profit(double money) {
        return money * 1.5;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isStay() {
        return false;
    }

    @Override
    public boolean isHit() {
        return false;
    }
}
