package state.started;

import card.Card;
import card.Cards;
import state.State;
import state.finished.Blackjack;
import state.running.Hit;

public abstract class Started implements State {

    protected Cards cards;

    protected Started(final Cards cards) {
        this.cards = cards;
    }

    public static Started start(final Card card1, final Card card2) {
        Cards cards = new Cards();
        cards.add(card1);
        cards.add(card2);

        if (cards.calculateScore() == 21 && cards.size() == 2) {
            return new Blackjack(cards);
        }

        return new Hit(cards);
    }

    public Cards cards() {
        return cards;
    }
}
