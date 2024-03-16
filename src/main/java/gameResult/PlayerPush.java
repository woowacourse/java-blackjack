package gameResult;

import participant.dealer.Dealer;
import participant.player.Player;

public class PlayerPush implements GameResult {

    @Override
    public boolean isCorrespondentResult(Player player, Dealer dealer) {
        return bothBlackJack(player, dealer) || bothSameScore(player, dealer);
    }

    @Override
    public double returnRate() {
        return 0;
    }

    private boolean bothBlackJack(Player player, Dealer dealer) {
        return player.isBlackJack() && dealer.isBlackJack();
    }

    private boolean bothSameScore(Player player, Dealer dealer) {
        return player.getCardScore() == dealer.getCardScore();
    }
}
