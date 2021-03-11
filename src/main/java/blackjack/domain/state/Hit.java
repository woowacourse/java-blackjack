package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.user.Cards;

public class Hit extends Running {
    public Hit(Cards cards) {
        super(cards);
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