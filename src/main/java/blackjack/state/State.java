package blackjack.state;

import blackjack.card.Card;
import blackjack.card.Hand;

public interface State {
    State hit(Card card);

    State stay();

    boolean isFinished();

    double getProfit(int bettingAmount);

    Hand getHand();
}
