package blackjack.blackjack.state.finished;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.StateType;
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
    public BigDecimal calculateProfit(final BigDecimal bettingAmount, final State dealerState) {
        if (dealerState.getStateType() == StateType.BUST) {
            return bettingAmount;
        }
        return compareByScore(bettingAmount, dealerState);
    }

    private BigDecimal compareByScore(final BigDecimal bettingAmount, final State dealerState) {
        int playerScore = cards.calculateScore();
        int dealerScore = dealerState.cards().calculateScore();
        if (playerScore < dealerScore) {
            return new Bust(cards).calculateProfit(bettingAmount, dealerState);
        }
        if (playerScore == dealerScore) {
            return BigDecimal.ZERO;
        }
        return bettingAmount;
    }
}
