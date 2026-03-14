package blackjack.domain.game;

import blackjack.domain.hand.Score;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum GameResult {
    BLACKJACK("블랙잭", "1.5"),
    WIN("승", "1.0"),
    LOSE("패", "-1.0"),
    DRAW("무", "0.0");

    private final String status;
    private final String ratio;

    GameResult(String status, String ratio) {
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
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }

        return judgeScoreResult(player, dealer);
    }

    private static GameResult judgeScoreResult(Player player, Dealer dealer) {
        Score playerScore = player.getScore();
        Score dealerScore = dealer.getScore();
        if (playerScore.isGreaterThan(dealerScore)) {
            return WIN;
        }
        if (dealerScore.isGreaterThan(playerScore)) {
            return LOSE;
        }
        return DRAW;
    }

    public String getRatio() {
        return ratio;
    }
}
