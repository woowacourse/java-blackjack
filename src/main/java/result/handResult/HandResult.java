package result.handResult;

import participant.Dealer;
import participant.Player;
import result.GameStatus;

public interface HandResult {
    GameStatus calculate(Player player, Dealer dealer);
}
