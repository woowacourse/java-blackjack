package gameResult;

import participant.dealer.Dealer;
import participant.player.Player;

public interface GameResult {

    boolean isCorrespondent(Player player, Dealer dealer);

    double returnRate();
}
