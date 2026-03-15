package domain.betting;

import domain.participant.Dealer;
import domain.participant.Player;

public enum BettingResult {
    WIN_WITH_BLACKJACK(150),
    WIN(100),
    DRAW(0),
    LOSE(-100);

    private final int earningRate;

    BettingResult(int earningRate) {
        this.earningRate = earningRate;
    }

    public static BettingResult judge(Player player, Dealer dealer) {
        if (player.isBust()) {
            return LOSE;
        }
        if (player.isBlackJack()) {
            return judgeBlackJack(dealer);
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return judgeScore(player, dealer);
    }

    private static BettingResult judgeBlackJack(Dealer dealer) {
        if (dealer.isBlackJack()) {
            return DRAW;
        }
        return WIN_WITH_BLACKJACK;
    }

    private static BettingResult judgeScore(Player player, Dealer dealer) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return LOSE;
    }

    public int getEarningRate() {
        return earningRate;
    }
}
