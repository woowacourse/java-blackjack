package blackjack.domain.cardgame;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

public enum WinningStatus {
    WIN,
    PUSH,
    LOSE,
    BLACKJACK;

    public static WinningStatus doesPlayerWin(final Dealer dealer, final Player player) {
        if (player.isBlackjack()) {
            return WinningStatus.BLACKJACK;
        }
        if (!player.isAlive()) {
            return WinningStatus.LOSE;
        }
        if (!dealer.isAlive()) {
            return WinningStatus.WIN;
        }
        if (dealer.score() == player.score()) {
            return WinningStatus.PUSH;
        }
        if (dealer.score() < player.score()) {
            return WinningStatus.WIN;
        }
        return WinningStatus.LOSE;
    }
}
