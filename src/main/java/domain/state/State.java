package domain.state;

import domain.deck.Card;
import domain.gamer.Hand;
import domain.state.type.StateType;

public interface State {
    State hit(final Card card);

    boolean isFinished();

    State stay();

    Hand getHand();

    int compareTo(final State other);

    StateType type();
}
