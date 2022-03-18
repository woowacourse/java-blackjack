package blackjack.domain.state;

import blackjack.domain.card.Card;

public abstract class Running extends Ready {

    Running() {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    public abstract State draw(Card card);

    public abstract State stay();
}
