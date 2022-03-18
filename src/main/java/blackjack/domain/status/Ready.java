package blackjack.domain.status;

import java.util.Set;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;

public class Ready implements Status {

    private final Cards cards;

    public Ready(Cards cards) {
        this.cards = cards;
    }

    public Ready() {
        this(new Cards(Set.of()));
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
