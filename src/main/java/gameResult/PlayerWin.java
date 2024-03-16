package gameResult;

import dealer.Dealer;
import player.Player;

public class PlayerWin implements GameResult {

    @Override
    public boolean isCorrespondent(Player player, Dealer dealer) {
        return player.getCardScore() > dealer.getCardScore();
    }

    @Override
    public double returnRate() {
        return 1;
    }
}
