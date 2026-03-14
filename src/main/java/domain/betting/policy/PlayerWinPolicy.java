package domain.betting.policy;

import domain.betting.BettingResult;
import domain.gamer.Dealer;
import domain.gamer.Player;

public class PlayerWinPolicy extends BettingPolicy{

    public PlayerWinPolicy() {
        super(BettingResult.PLAYER_WIN);
    }

    @Override
    public boolean isPolicyApplied(Dealer dealer, Player player) {
        if ((player.isStand() && dealer.isStand())) {
            return isPlayerCompareWin(dealer.getResultScore(), player.getResultScore());
        }

        return (player.isBusted() && dealer.isBusted()) ||
                (dealer.isBusted() && player.isStand());
    }

    private boolean isPlayerCompareWin(int dealerScore, int playerScore) {
        return playerScore >= dealerScore;
    }

    @Override
    public double getBettingRate(Dealer dealer, Player player) {
        return bettingResult.bettingRate();
    }
}
