package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Blackjack implements State {

    private final Cards cards;

    protected Blackjack(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(Card card) {
        return null;
    }

    @Override
    public State stay() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
