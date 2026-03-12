package domain.betting.policy;

import domain.betting.BettingResult;
import domain.betting.BettingRate;
import domain.gamer.Dealer;
import domain.gamer.Player;

public class DoubleBustPolicy extends BettingPolicy{

    public DoubleBustPolicy() {
        super(BettingResult.PLAYER_WIN);
    }

    @Override
    public boolean isPolicyApplied(Dealer dealer, Player player) {
        return player.isBusted() && dealer.isBusted();
    }

    @Override
    public BettingRate getBettingRate(Dealer dealer, Player player) {
        return bettingResult.bettingRate();
    }

}
