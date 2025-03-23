package blackjack.domain.state;

import blackjack.domain.card.Hand;
import java.math.BigDecimal;

public class Stay extends Finished {

    public Stay(final Hand cards) {
        super(cards, StateType.STAY);
    }

    @Override
    public State stay() {
        throw new IllegalStateException("[ERROR] 현재 Stay 상태입니다.");
    }

    @Override
    public BigDecimal profit(final BigDecimal bettingAmount, final State dealerState) {
        if (dealerState.getStateType() == StateType.BUST) {
            return bettingAmount;
        }
        if (dealerState.getStateType() == StateType.BLACKJACK) {
            return bettingAmount.multiply(BigDecimal.valueOf(-1));
        }
        return compareByScore(bettingAmount, dealerState);
    }

    private BigDecimal compareByScore(final BigDecimal bettingAmount, final State dealerState) {
        int playerScore = cards.calculateScore();
        int dealerScore = dealerState.cards().calculateScore();
        if (playerScore < dealerScore) {
            return new Bust(cards).profit(bettingAmount, dealerState);
        }
        if (playerScore == dealerScore) {
            return BigDecimal.ZERO;
        }
        return bettingAmount;
    }
}
