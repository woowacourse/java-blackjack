package domain.betting.policy;

import domain.betting.BettingRate;
import domain.betting.BettingResult;
import domain.gamer.Dealer;
import domain.gamer.Player;

public class PlayerLosePolicy extends BettingPolicy{

    public PlayerLosePolicy() {
        super(BettingResult.PLAYER_LOSE);
    }

    @Override
    public boolean isPolicyApplied(Dealer dealer, Player player) {
        if ((player.isStand() && dealer.isStand())) {
            return isPlayerCompareLose(dealer.getResultScore(), player.getResultScore());
        }

        return player.isBusted() && !dealer.isBusted();
    }

    private boolean isPlayerCompareLose(int dealerScore, int playerScore) {
        return playerScore < dealerScore;
    }

    @Override
    public BettingRate getBettingRate(Dealer dealer, Player player) {
        return bettingResult.bettingRate();
    }
}
