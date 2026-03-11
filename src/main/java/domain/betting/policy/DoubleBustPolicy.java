package domain.betting.policy;

import domain.analyzer.BettingResult;
import domain.betting.BettingRate;
import domain.gamer.Dealer;
import domain.gamer.Player;

public class DoubleBustPolicy extends BettingPolicy{

    public DoubleBustPolicy() {
        super(BettingResult.DOUBLE_BUST.bettingRate());
    }

    @Override
    public boolean isPolicyApplied(Dealer dealer, Player player) {
        return player.isBusted() && dealer.isBusted();
    }

    @Override
    public BettingRate getBettingRate(Dealer dealer, Player player) {
        return this.bettingRate;
    }

}
