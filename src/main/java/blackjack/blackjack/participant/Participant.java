package blackjack.blackjack.participant;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;

public interface Participant {

    Hand showInitialCards();

    boolean canHit();

    int calculateScore();

    void changeState(State inputState);

    Hand showAllCards();

    String getNickname();
}
