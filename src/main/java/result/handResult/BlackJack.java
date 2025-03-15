package result.handResult;

import static result.GameStatus.*;

import participant.Dealer;
import participant.Player;
import result.GameStatus;

public class BlackJack implements HandResult {
    @Override
    public GameStatus calculate(Player player, Dealer dealer) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return DRAW;
        }
        if (player.isBlackJack()) {
            return BLACKJACK_WIN;
        }
        if (dealer.isBlackJack()) {
            return LOSE;
        }
        return UNDECIDED;
    }
}
