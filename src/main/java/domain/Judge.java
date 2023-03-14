package domain;

import domain.gamestate.GameState;
import domain.gamestate.GameStates;
import domain.participant.Dealer;
import domain.participant.Player;

public class Judge {

    public static GameState gameResult(Dealer dealer, Player player) {
        if (player.isBurst() || dealerIsHigherThanPlayer(dealer, player)) {
            return GameStates.LOSE;
        }
        if (dealer.isBurst() || player.isHighScoreThan(dealer)) {
            return getWinState(player);
        }
        return GameStates.DRAW;
    }

    private static boolean dealerIsHigherThanPlayer(Dealer dealer, Player player) {
        return dealer.isHighScoreThan(player) && !dealer.isBurst();
    }

    private static GameState getWinState(Player player) {
        if (player.isBlackJack()) {
            return GameStates.BLACKJACK;
        }
        return GameStates.WIN;
    }
}
