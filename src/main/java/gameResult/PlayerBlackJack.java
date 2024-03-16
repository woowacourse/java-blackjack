package gameResult;

import dealer.Dealer;
import player.Player;

public class PlayerBlackJack implements GameResult {

    @Override
    public boolean isCorrespondent(Player player, Dealer dealer) {
        return player.isBlackJack();
    }

    @Override
    public double returnRate() {
        return 1.5;
    }
}
