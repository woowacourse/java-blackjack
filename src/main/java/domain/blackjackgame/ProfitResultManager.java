package domain.blackjackgame;

import domain.participant.Dealer;
import domain.participant.Player;
import java.math.BigDecimal;

public class ProfitResultManager {
    private final Dealer dealer;

    protected ProfitResultManager(Dealer dealer) {
        this.dealer = dealer;
    }

    protected BigDecimal calculateProfit(Player player) {
        EarningRate earningRate = findEarningRate(player);
        return earningRate.calculateProfit(player.getBetAmount());
    }

    private EarningRate findEarningRate(Player player) {
        if (player.isBust()) {
            return EarningRate.LOSE;
        }
        if (player.isBlackjack()) {
            return compareBlackjackState();
        }
        return compareStayState(player.calculateScore());
    }

    private EarningRate compareBlackjackState() {
        if (dealer.isBlackjack()) {
            return EarningRate.PUSH;
        }
        return EarningRate.BLACKJACK;
    }

    private EarningRate compareStayState(int playerScore) {
        int dealerScore = dealer.calculateScore();

        if (dealer.isBust() || playerScore > dealerScore) {
            return EarningRate.WIN;
        }
        if (dealer.isBlackjack() || playerScore < dealerScore) {
            return EarningRate.LOSE;
        }
        return EarningRate.PUSH;
    }

    private enum EarningRate {
        WIN(BigDecimal.ONE),
        PUSH(BigDecimal.ZERO),
        LOSE(BigDecimal.ONE.negate()),
        BLACKJACK(new BigDecimal("1.5"));

        private final BigDecimal rate;

        EarningRate(BigDecimal rate) {
            this.rate = rate;
        }

        public BigDecimal calculateProfit(int betAmount) {
            return BigDecimal.valueOf(betAmount).multiply(rate);
        }
    }
}
