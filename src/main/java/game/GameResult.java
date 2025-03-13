package game;

import participant.Dealer;
import participant.Player;

public enum GameResult {

    BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0),
    ;

    private final double rate;

    GameResult(double rate) {
        this.rate = rate;
    }

    public static GameResult judgePlayerResult(Dealer dealer, Player player) {
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return BLACKJACK;
        }
        if (!player.isBust() && (player.score() > dealer.score() || dealer.isBust())) {
            return WIN;
        }
        if (!dealer.isBust() && (player.score() < dealer.score() || player.isBust())) {
            return LOSE;
        }
        return DRAW;
    }

    public double getRate() {
        return rate;
    }
}
