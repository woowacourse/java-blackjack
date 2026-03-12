package domain.betting.policy;

import domain.betting.BettingResult;
import domain.betting.BettingRate;
import domain.gamer.Dealer;
import domain.gamer.Player;

public class BustPolicy extends BettingPolicy{

    public BustPolicy() {
        super(BettingResult.PLAYER_BUST);
    }

    @Override
    public boolean isPolicyApplied(Dealer dealer, Player player) {
        return player.isBusted() && !dealer.isBusted();
    }

    @Override
    public BettingRate getBettingRate(Dealer dealer, Player player) {
        return bettingResult.bettingRate();
    }

}
