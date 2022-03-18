package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.PlayerCards;
import java.util.Collections;
import java.util.List;

public class Ready implements State {

    protected PlayerCards cards;

    public Ready() {
        cards = new PlayerCards();
    }

    @Override
    public State draw(Card card) {
        cards.add(card);
        if (cards.isReady()) {
            return this;
        }
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }

    @Override
    public State stay() {
        throw new IllegalStateException();
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.get());
    }
}
