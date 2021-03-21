package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum GameResult {

    WIN("승", 0.0d),
    LOSE("패", -1.0d),
    TIE("무", 0.0d);

    private final String name;
    private final double earningRate;

    GameResult(String name, double earningRate) {
        this.name = name;
        this.earningRate = earningRate;
    }

    public static double calculateEarning(Dealer dealer, Player player) {
        GameResult gameResult = dealer.judgePlayer(player);
        if (gameResult == GameResult.WIN) {
            return player.profit();
        }
        return player.profit(gameResult.earningRate);
    }

    public String getName() {
        return name;
    }
}
