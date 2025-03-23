package blackjack.blackjack.participant;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;

public abstract class Participant {

    public abstract Hand showInitialCards();

    public abstract boolean canHit();

    public abstract int calculateScore();

    public abstract void changeState(State inputState);

    public abstract Hand showAllCards();

    public abstract String getNickname();
}
