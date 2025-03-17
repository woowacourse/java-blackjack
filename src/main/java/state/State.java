package state;

import card.Card;
import card.CardHand;
import card.GameScore;

public interface State {
    State receiveCard(final Card card);

    State stay();

    boolean isFinished();

    CardHand cardHand();

    GameScore calculateScore();
}
