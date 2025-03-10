package blackjack.domain;

import blackjack.domain.player.Player;

public enum GameResult {

    WIN,
    LOSE,
    DRAW;

    public static GameResult evaluateGameResult(final Player dealer, final Player gambler) {
        if (dealer.compareWithOtherPlayer(gambler) > 0) {
            return LOSE;
        }
        if (dealer.compareWithOtherPlayer(gambler) < 0) {
            return WIN;
        }
        return DRAW;
    }
}
