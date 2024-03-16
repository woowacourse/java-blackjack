package gameResult;

import participant.dealer.Dealer;
import participant.player.Player;

public class PlayerLose implements GameResult {

    @Override
    public boolean isCorrespondentResult(Player player, Dealer dealer) {
        return player.getCardScore() < dealer.getCardScore();
    }

    @Override
    public double returnRate() {
        return -1;
    }
}
