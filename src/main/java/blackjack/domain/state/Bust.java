package blackjack.domain.state;

import blackjack.domain.card.Hand;
import java.math.BigDecimal;

public final class Bust extends Finished {

    public Bust(final Hand cards) {
        super(cards, StateType.BUST);
    }

    @Override
    public State receiveCards(final Hand hand) {
        cards.addAll(hand);
        return this;
    }

    @Override
    public BigDecimal profit(final BigDecimal bettingAmount, final State dealerState) {
        return bettingAmount.multiply(BigDecimal.valueOf(-1));
    }
}
