package blackjack.domain.betting;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

public enum GameResult {
    WIN,
    PUSH,
    LOSE,
    BLACKJACK;

    public static GameResult doesPlayerWin(final Dealer dealer, final Player player) {
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
}
