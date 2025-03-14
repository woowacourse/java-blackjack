package result.handStatus;

import participant.Dealer;
import participant.Player;
import result.GameStatus;

public interface HandStatus {
    GameStatus calculateResult(Player player, Dealer dealer);
}
