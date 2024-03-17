package blackjack.domain.cardgame;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

public enum GameResult {
    WIN,
    PUSH,
    LOSE,
    BLACKJACK;

    public static GameResult doesPlayerWin(final Dealer dealer, final Player player) {
        // TODO: 딜러와 플레이어 모두 블랙잭인 경우를 고려하라
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
