package domain.betting.policy;

import domain.analyzer.BettingResult;
import domain.betting.BettingRate;
import domain.gamer.Dealer;
import domain.gamer.Player;

public class DealerBustPolicy extends BettingPolicy {
    public DealerBustPolicy() {
        super(BettingResult.DEALER_BUST);
    }

    @Override
    public boolean isPolicyApplied(Dealer dealer, Player player) {
        return dealer.isBusted() && player.isStop();
    }

    @Override
    public BettingRate getBettingRate(Dealer dealer, Player player) {
        return bettingResult.bettingRate();
    }
}
