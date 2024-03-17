package domain.blackjackgame;

import domain.participant.Dealer;
import domain.participant.Player;

public class ProfitResultManager {
    private final Dealer dealer;

    protected ProfitResultManager(Dealer dealer) {
        this.dealer = dealer;
    }

    protected int calculateProfit(Player player) {
        EarningRate earningRate = findEarningRate(player);
        return earningRate.calculateProfit(player.getBettingAmount());
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
        WIN(1),
        PUSH(0),
        LOSE(-1),
        BLACKJACK(1.5);

        private final double rate;

        EarningRate(double rate) {
            this.rate = rate;
        }

        public int calculateProfit(int betAmount) {
            return (int) (betAmount * rate);
        }
    }
}
