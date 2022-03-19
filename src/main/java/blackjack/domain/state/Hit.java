package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;

public final class Hit extends Running {

    Hit(HoldCards holdCards) {
        super(holdCards);
    }

    @Override
    public State draw(Card card) {
        holdCards().addCard(card);
        if (holdCards().isBust()) {
            return new Bust(holdCards());
        }
        return new Hit(holdCards());
    }

    @Override
    public State stay() {
        return new Stay(holdCards());
    }
}
