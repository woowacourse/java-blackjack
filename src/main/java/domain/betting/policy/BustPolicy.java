package domain.betting.policy;

import domain.betting.BettingResult;
import domain.gamer.Dealer;
import domain.gamer.Player;

public class BustPolicy extends BettingPolicy{

    public BustPolicy() {
        super(BettingResult.PLAYER_LOSE);
    }

    @Override
    public boolean isPolicyApplied(Dealer dealer, Player player) {
        return player.isBusted() && !dealer.isBusted();
    }

    @Override
    public double getBettingRate(Dealer dealer, Player player) {
        return bettingResult.bettingRate();
    }

}
