package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum GameResult {

    WIN(0.0d),
    LOSE(-1.0d),
    TIE(0.0d);

    private final double earningRate;

    GameResult(double earningRate) {
        this.earningRate = earningRate;
    }

    public static double calculateEarning(Dealer dealer, Player player) {
        GameResult gameResult = dealer.judgePlayer(player);
        if (gameResult == GameResult.WIN) {
            return player.profit();
        }
        return player.profit(gameResult.earningRate);
    }
}
