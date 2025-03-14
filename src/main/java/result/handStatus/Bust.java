package result.handStatus;

import static result.GameStatus.*;

import participant.Dealer;
import participant.Player;
import result.GameStatus;

public class Bust implements HandStatus {
    @Override
    public GameStatus calculateResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return UNDECIDED;
    }
}
