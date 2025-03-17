package state;

import card.Card;
import card.CardHand;

public interface State {
    State receiveCard(final Card card);

    State stay();

    boolean isFinished();

    CardHand cardHand();

//    Bet profit(final Bet bettingAmount);
}
