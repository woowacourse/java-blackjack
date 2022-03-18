package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

public class Hit implements State {

    private final Cards cards;

    protected Hit(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public State add(Card card) {
        return null;
    }
}
