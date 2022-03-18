package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

public final class Ready implements State {

    private final Cards cards;

    public Ready() {
        this.cards = new Cards();
    }

    private Ready(final Cards cards) {
        this.cards = cards;
    }

    public State add(final Card card) {
        Cards cards = this.cards.add(card);

        if (cards.isBlackjack()){
            return new Blackjack(cards);
        }
        if (cards.canHit()) {
            return new Hit(cards);
        }
        return new Ready(cards);
    }

    @Override
    public boolean isReady() {
        return true;
    }
}
