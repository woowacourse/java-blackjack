package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Blackjack implements State {

    private final Cards cards;

    protected Blackjack(Cards cards) {
        this.cards = cards;
        validate();
    }

    private void validate() {
        if (!cards.isBlackjack()) {
            throw new IllegalArgumentException();
        }
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
}
