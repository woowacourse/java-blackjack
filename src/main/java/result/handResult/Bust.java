package result.handResult;

import static result.GameStatus.*;

import participant.Dealer;
import participant.Player;
import result.GameStatus;

public class Bust implements HandResult {
    @Override
    public GameStatus calculate(Player player, Dealer dealer) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return UNDECIDED;
    }
}
