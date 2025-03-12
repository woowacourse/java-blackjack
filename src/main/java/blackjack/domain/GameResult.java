package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;

public enum GameResult {

    WIN,
    LOSE,
    DRAW;

    public static GameResult evaluateGameResult(final Dealer dealer, final Gambler gambler) {
        int compared = dealer.compareWithOtherPlayer(gambler);
        if (compared > 0) {
            return LOSE;
        }
        if (compared < 0) {
            return WIN;
        }
        return DRAW;
    }
}
