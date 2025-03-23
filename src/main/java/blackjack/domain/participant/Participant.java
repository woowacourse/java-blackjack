package blackjack.domain.participant;

import blackjack.domain.card.Hand;
import blackjack.domain.state.State;

public abstract class Participant {

    public abstract Hand showInitialCards();

    public abstract boolean canHit();

    public abstract String getNickname();

    public abstract Hand showAllCards();

    public abstract int calculateScore();

    public abstract void changeState(State inputState);
}
