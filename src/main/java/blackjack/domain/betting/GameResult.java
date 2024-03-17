package blackjack.domain.betting;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

public enum GameResult {
    WIN(1.0),
    PUSH(0.0),
    LOSE(-1.0),
    BLACKJACK(1.5);

    private final double profitRate;

    GameResult(final double profitRate) {
        this.profitRate = profitRate;
    }

    static GameResult doesPlayerWin(final Dealer dealer, final Player player) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return GameResult.PUSH;
        }
        if (player.isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        if (!player.isAlive()) {
            return GameResult.LOSE;
        }
        if (!dealer.isAlive()) {
            return GameResult.WIN;
        }
        if (dealer.score() == player.score()) {
            return GameResult.PUSH;
        }
        if (dealer.score() < player.score()) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }

    double getProfitRate() {
        return profitRate;
    }
}
