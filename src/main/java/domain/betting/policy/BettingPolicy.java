package domain.betting.policy;

import domain.analyzer.BettingResult;
import domain.betting.BettingRate;
import domain.gamer.Dealer;
import domain.gamer.Player;

public abstract class BettingPolicy {

    protected BettingResult bettingResult;

    public BettingPolicy(BettingResult bettingResult) {
        this.bettingResult = bettingResult;
    }

    public abstract boolean isPolicyApplied(Dealer dealer, Player player);

    public abstract BettingRate getBettingRate(Dealer dealer, Player player);

}
