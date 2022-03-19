package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;

public class Ready extends Running {

    protected Ready(HoldCards holdCards) {
        super(holdCards);
    }

    public static State start(Card first, Card card2) {
        HoldCards holdCards = new HoldCards();
        Ready ready = new Ready(holdCards);
        return ready.draw(first).draw(card2);
    }

    @Override
    public State draw(Card card) {
        holdCards().addCard(card);
        if (!holdCards().isReady()) {
            return new Ready(holdCards());
        }
        return blackjack();
    }

    @Override
    public State stay() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isReady() {
        return true;
    }
}
