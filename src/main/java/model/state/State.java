package model.state;

import model.card.Card;
import model.card.CardHand;

public interface State {
    State receiveCard(final Card card);

    State stay();

    boolean isFinished();

    CardHand cardHand();

    int profit(final int bettingAmount);
}
