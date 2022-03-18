package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit extends Running {

    Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        Cards cards = this.cards().add(card);
        if (cards.isBust2()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards());
    }
}
