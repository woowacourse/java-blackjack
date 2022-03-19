package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit extends Running {

    protected Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        cards.append(card);

        if (cards.isBust()) {
            return new Bust(cards);
        }

        return new Hit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }
}
