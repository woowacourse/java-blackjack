package gameResult;

import participant.dealer.Dealer;
import participant.player.Player;

public interface GameResult {

    boolean isCorrespondentResult(Player player, Dealer dealer);

    double returnRate();
}
