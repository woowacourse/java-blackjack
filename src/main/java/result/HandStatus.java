package result;

import participant.Dealer;
import participant.Player;

public interface HandStatus {
    GameStatus calculateResult(Player player, Dealer dealer);
}
