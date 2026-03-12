package domain.betting.policy;

import domain.betting.BettingResult;
import domain.betting.BettingRate;
import domain.gamer.Dealer;
import domain.gamer.Player;

public class DealerBustPolicy extends BettingPolicy {

    public DealerBustPolicy() {
        super(BettingResult.PLAYER_WIN);
    }

    @Override
    public boolean isPolicyApplied(Dealer dealer, Player player) {
        return dealer.isBusted() && player.isStand();
    }

    @Override
    public BettingRate getBettingRate(Dealer dealer, Player player) {
        return bettingResult.bettingRate();
    }

}
