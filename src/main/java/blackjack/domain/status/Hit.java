package blackjack.domain.status;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;

public class Hit implements Status {

    private final Cards cards;

    public Hit(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Status draw(Card card) {
        return new Ready(cards.add(card));
    }

    @Override
    public Cards getCards() {
        return cards;
    }
}
