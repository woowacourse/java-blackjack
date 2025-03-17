package game;

import participant.Dealer;
import participant.Player;

public enum GameResult {

    BLACKJACK(1.5, true),
    WIN(1, true),
    LOSE(1, false),
    DRAW(0, true),
    ;

    private final double profitRate;
    private final boolean isProfitable;

    GameResult(double profitRate, boolean isProfitable) {
        this.profitRate = profitRate;
        this.isProfitable = isProfitable;
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

    public boolean isProfitable() {
        return isProfitable;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
