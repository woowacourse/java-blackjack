package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit extends Running {

    Hit(Cards cards) {
        super(cards);
    }

    @Override
    public final State draw(Card card) {
        final Cards cards = getCards();
        cards.receiveCard(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }
}
