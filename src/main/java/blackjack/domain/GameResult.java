package blackjack.domain;

import blackjack.domain.player.Player;

public enum GameResult {

    WIN,
    LOSE,
    DRAW;

    public static GameResult evaluateGameResult(Player dealer, Player gambler) {
        if (dealer.compareWithOtherPlayer(gambler) > 0) {
            return LOSE;
        }
        if (dealer.compareWithOtherPlayer(gambler) < 0) {
            return WIN;
        }
        return DRAW;
    }
}
