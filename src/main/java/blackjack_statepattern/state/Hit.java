package blackjack_statepattern.state;

import blackjack_statepattern.card.Card;
import blackjack_statepattern.card.Cards;

public final class Hit extends Running {

    Hit(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public State draw(final Card card) {
        Cards cards = cards().add(card);

        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards());
    }
}
