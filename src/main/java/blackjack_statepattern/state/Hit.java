package blackjack_statepattern.state;

import blackjack_statepattern.Card;
import blackjack_statepattern.Cards;

public class Hit implements State {

    private final Cards cards;

    Hit(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(final Card card) {
        Cards cards = this.cards.add(card);

        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }
}
