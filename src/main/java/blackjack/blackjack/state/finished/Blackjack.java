package blackjack.blackjack.state.finished;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.StateType;

public final class Blackjack extends Finished {

    public Blackjack(final Hand cards) {
        super(cards, StateType.BLACKJACK);
    }
}
