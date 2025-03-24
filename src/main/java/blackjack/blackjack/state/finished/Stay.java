package blackjack.blackjack.state.finished;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.participant.Dealer;
import blackjack.blackjack.state.StateType;
import java.math.BigDecimal;

public class Stay extends Finished {

    public Stay(final Hand hand) {
        super(hand, StateType.STAY);
    }

    @Override
    public BigDecimal calculateProfit(final BigDecimal bettingAmount, final Dealer dealer) {
        if (dealer.getStateType() == StateType.BUST) {
            return bettingAmount;
        }
        return compareByScore(bettingAmount, dealer);
    }

    private BigDecimal compareByScore(final BigDecimal bettingAmount, final Dealer dealer) {
        int playerScore = hand.calculateScore();
        int dealerScore = dealer.calculateScore();
        if (playerScore < dealerScore) {
            return new Bust(hand).calculateProfit(bettingAmount, dealer);
        }
        if (playerScore == dealerScore) {
            return BigDecimal.ZERO;
        }
        return bettingAmount;
    }
}
