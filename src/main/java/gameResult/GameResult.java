package gameResult;

import dealer.Dealer;
import player.Player;

public interface GameResult {

    boolean isCorrespondent(Player player, Dealer dealer);

    double returnRate();
}
