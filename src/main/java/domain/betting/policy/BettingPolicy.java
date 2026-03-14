package domain.betting.policy;

import domain.betting.BettingResult;
import domain.gamer.Dealer;
import domain.gamer.Player;

public abstract class BettingPolicy {

    protected BettingResult bettingResult;

    public BettingPolicy(BettingResult bettingResult) {
        this.bettingResult = bettingResult;
    }

    public abstract boolean isPolicyApplied(Dealer dealer, Player player);

    public abstract double getBettingRate(Dealer dealer, Player player);

}
