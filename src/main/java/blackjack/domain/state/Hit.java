package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit extends Running {
    private static final int TWENTY_ONE = 21;

    public Hit(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(final Card card) {
        cards.add(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        if (cards.calculate() == TWENTY_ONE) {
            return stay();
        }
        return new Hit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }
}
