package domain.betting;

import domain.participant.Dealer;
import domain.participant.Player;

public enum GameResult {
    WIN_WITH_BLACKJACK(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0);

    private final double earningRate;

    GameResult(double earningRate) {
        this.earningRate = earningRate;
    }

    public static GameResult judge(Player player, Dealer dealer) {
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

    private static GameResult judgeBlackJack(Dealer dealer) {
        if (dealer.isBlackJack()) {
            return DRAW;
        }
        return WIN_WITH_BLACKJACK;
    }

    private static GameResult judgeScore(Player player, Dealer dealer) {
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

    public double getEarningRate() {
        return earningRate;
    }
}
