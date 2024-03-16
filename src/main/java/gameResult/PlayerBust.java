package gameResult;

import dealer.Dealer;
import player.Player;

public class PlayerBust implements GameResult {

    @Override
    public boolean isCorrespondent(Player player, Dealer dealer) {
        return player.isBust();
    }

    @Override
    public double returnRate() {
        return -1;
    }
}
