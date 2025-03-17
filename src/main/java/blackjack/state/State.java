package blackjack.state;

import blackjack.card.Card;
import blackjack.card.Hand;
import java.math.BigDecimal;

public interface State {
    State hit(Card card);

    State stay();

    boolean isFinished();

    BigDecimal getProfit(BigDecimal bettingAmount);

    Hand getHand();
}
