package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;

public class Ready extends Running {

    protected Ready(HoldCards holdCards) {
        super(holdCards);
    }

    public static State start(Card first, Card second) {
        HoldCards holdCards = new HoldCards();
        Ready ready = new Ready(holdCards);
        return ready.draw(first).draw(second);
    }

    @Override
    public State draw(Card card) {
        getHoldCards().addCard(card);
        if (!getHoldCards().isReady()) {
            return new Ready(getHoldCards());
        }
        return checkBlackjack();
    }

    @Override
    public State stay() {
        throw new IllegalStateException("Ready 상태에서 Stay 할 수 없습니다.");
    }
}
