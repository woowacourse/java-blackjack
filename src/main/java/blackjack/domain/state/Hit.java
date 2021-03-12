package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit extends Running {
    public Hit(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(final Card card) {
        Cards cards = cards();
        cards.add(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        if (cards.isBlackJack()) {
            return new BlackJack(cards);
        }
        return new Hit(cards);
    }
}
