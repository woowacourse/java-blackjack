package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public enum GameResult {
    BLACKJACK,
    WIN,
    DRAW,
    LOSE;

    public static GameResult getGameResult(final Player player, final Dealer dealer) {
        if (player.isBlackjack()) {
            if (dealer.isBlackjack()) {
                return GameResult.DRAW;
            }
            return GameResult.BLACKJACK;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        if (player.getScore() > dealer.getScore()) {
            return GameResult.WIN;
        }
        if (player.getScore() == dealer.getScore()) {
            return GameResult.DRAW;
        }
        if (player.getScore() < dealer.getScore()) {
            return GameResult.LOSE;
        }
        return GameResult.LOSE;
    }
}
