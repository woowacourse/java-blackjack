package blackjack.domain.gameresult;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum GameResult {
    BLACKJACK("블랙잭", 1.5),
    WIN("승", 1.0),
    LOSE("패", -1.0),
    DRAW("무", 0.0);

    private final String status;
    private final double ratio;

    GameResult(String status, double ratio) {
        this.status = status;
        this.ratio = ratio;
    }

    public static GameResult matchResult(Player player, Dealer dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return DRAW;
        }
        if (player.isBlackjack()) {
            return BLACKJACK;
        }
        if (dealer.isBlackjack()) {
            return LOSE;
        }
        if (player.isBurst()) {
            return LOSE;
        }
        if (dealer.isBurst()) {
            return WIN;
        }

        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        if (playerScore < dealerScore) {
            return LOSE;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return WIN;
    }

    public String getStatus() {
        return status;
    }

    public double getRatio() {
        return ratio;
    }
}
