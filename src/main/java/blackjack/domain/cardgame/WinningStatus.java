package blackjack.domain.cardgame;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

public enum WinningStatus {
    WIN,
    PUSH,
    LOSE;

    public static WinningStatus doesPlayerWin(final Dealer dealer, final Player player) {
        if (!player.isAlive()) {
            return WinningStatus.LOSE;
        }
        if (!dealer.isAlive()) {
            return WinningStatus.WIN;
        }
        if (dealer.getScore() == player.getScore()) {
            return WinningStatus.PUSH;
        }
        if (dealer.getScore() < player.getScore()) {
            return WinningStatus.WIN;
        }
        return WinningStatus.LOSE;
    }
}
