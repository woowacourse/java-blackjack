package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Stay implements State {

    private final Cards cards;

    protected Stay(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException();
    }

    @Override
    public State stay() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public double earningRate(State state) {
        Cards otherCards = state.cards();

        if (otherCards.isBust() || cards.totalScore() > otherCards.totalScore()) {
            return 1;
        }

        if (cards.totalScore() == otherCards.totalScore()) {
            return 0;
        }

        return -1;
    }
}
