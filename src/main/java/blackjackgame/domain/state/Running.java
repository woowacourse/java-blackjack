package blackjackgame.domain.state;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Hand;

public abstract class Running extends State {

    Running(Hand hand) {
        super(hand);
    }

    @Override
    public State stay() {
        return new Stay(hand());
    }

    protected Hand add(final Card card) {
        return hand().add(card);
    }
}
