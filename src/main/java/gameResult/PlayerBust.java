package gameResult;

import participant.dealer.Dealer;
import participant.player.Player;

public class PlayerBust implements GameResult {

    @Override
    public boolean isCorrespondentResult(Player player, Dealer dealer) {
        return player.isBust();
    }

    @Override
    public double returnRate() {
        return -1;
    }
}
