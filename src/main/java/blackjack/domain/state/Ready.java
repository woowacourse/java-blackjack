package blackjack.domain.state;

import java.util.Set;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;

public class Ready implements State {

    private final Cards cards;

    public Ready(Cards cards) {
        this.cards = cards;
    }

    public Ready() {
        this(new Cards(Set.of()));
    }

    @Override
    public State draw(Card card) {
        Cards newCards = cards.add(card);

        if (newCards.isBlackjack()) {
            return new Blackjack(newCards);
        }

        if (newCards.size() == 2) {
            return new Hit(newCards);
        }

        return new Ready(newCards);
    }

    @Override
    public Cards getCards() {
        return cards;
    }
}
