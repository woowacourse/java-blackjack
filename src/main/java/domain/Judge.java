package domain;

import domain.participant.Dealer;
import domain.participant.Player;

public class Judge {

    public static GameState gameResult(Dealer dealer, Player player) {
        if (player.isBurst() || dealerIsHigherThanPlayer(dealer, player)) {
            return GameState.LOSE;
        }
        if (dealer.isBurst() || player.isHighScoreThan(dealer)) {
            return isBlackJackWin(player);
        }
        return GameState.DRAW;
    }

    private static boolean dealerIsHigherThanPlayer(Dealer dealer, Player player) {
        return dealer.isHighScoreThan(player) && !dealer.isBurst();
    }

    private static GameState isBlackJackWin(Player player) {
        if (player.isBlackJack()) {
            return GameState.BLACKJACK;
        }
        return GameState.WIN;
    }
}
