package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Ready implements Status {

    private final Cards cards;

    public Ready(Cards cards) {
        this.cards = cards;
    }

    public Ready() {
        this(new Cards());
    }

    @Override
    public Status draw(Card card) {
        cards.receiveCard(card);
        if (cards.isReady()) {
            return new Ready(cards);
        }
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }
}
