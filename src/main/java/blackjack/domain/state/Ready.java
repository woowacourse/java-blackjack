package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Ready extends Started {

    public Ready(Cards cards) {
        super(cards);
    }

    public Ready() {
        this(new Cards());
    }

    @Override
    public final State draw(Card card) {
        final Cards cards = getCards();
        cards.receiveCard(card);
        if (cards.isReady()) {
            return this;
        }
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }
}
