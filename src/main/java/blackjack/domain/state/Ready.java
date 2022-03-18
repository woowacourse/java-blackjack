package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Ready extends Running {

    public Ready() {
        this(new Cards());
    }

    private Ready(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        final Cards cards = cards().add(card);
        if (cards.isReady()) {
            return new Ready(cards);
        }
        if (cards.isBlackJack()) {
            return new BlackJack(cards);
        }
        return new Hit(cards);
    }

    @Override
    public State stay() {
        throw new IllegalStateException();
    }

}
