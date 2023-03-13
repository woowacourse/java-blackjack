package domain;

import domain.gamestate.GameState;
import domain.gamestate.GameStates;
import domain.participant.Dealer;
import domain.participant.Player;

public class Judge {

    public static GameState gameResult(Dealer dealer, Player player) {
        if (player.isBurst() || dealer.isHighScoreThan(player) && !dealer.isBurst()) {
            return GameStates.LOSE;
        }
        if (dealer.isBurst() || player.isHighScoreThan(dealer)) {
            if (player.isBlackJack()) {
                return GameStates.BLACKJACK;
            }
            return GameStates.WIN;
        }
        return GameStates.DRAW;
    }
}
