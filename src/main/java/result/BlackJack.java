package result;

import static result.GameStatus.*;

import participant.Dealer;
import participant.Player;

public class BlackJack implements HandStatus {
    @Override
    public GameStatus calculateResult(Player player, Dealer dealer) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return DRAW;
        }
        if (player.isBlackJack()) {
            return WIN;
        }
        if (dealer.isBlackJack()) {
            return LOSE;
        }
        return UNDECIDED;
    }
}
