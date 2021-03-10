package blackjack.domain.state;

import blackjack.domain.Card;
import blackjack.domain.Cards;

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
        return new Hit(cards);
    }

    public State stay() {
        return new Stay(cards);
    }
}
