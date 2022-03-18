package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.PlayerCards;

public class Hit extends Ready {

    Hit(PlayerCards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(Card card) {
        cards.add(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return this;
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }
}
