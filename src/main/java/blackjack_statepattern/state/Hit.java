package blackjack_statepattern.state;

import blackjack_statepattern.Card;
import blackjack_statepattern.Cards;

public class Hit extends Started {


    Hit(Cards cards) {
        super(cards);
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
