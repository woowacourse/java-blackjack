package blackjack.domain.card.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Ready extends AbstractState {
    public Ready() {
        this(new Cards());
    }

    public Ready(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(final Card card) {
        final var newCards = cards().draw(card);
        if (newCards.calculate() == 21 && newCards.size() == 2) {
            return new Blackjack(newCards);
        }
        if (newCards.size() == 2) {
            return new Hit(newCards);
        }
        return new Ready(newCards);
    }
}
