package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;

public final class Hit extends Running {

    Hit(HoldCards holdCards) {
        super(holdCards);
    }

    @Override
    public State draw(Card card) {
        getHoldCards().addCard(card);
        if (getHoldCards().isBust()) {
            return new Bust(getHoldCards());
        }
        return new Hit(getHoldCards());
    }

    @Override
    public State stay() {
        return new Stay(getHoldCards());
    }
}
