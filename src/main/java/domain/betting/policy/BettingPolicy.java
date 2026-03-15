package domain.betting.policy;

import domain.betting.BettingRate;
import domain.gamer.Dealer;
import domain.gamer.Player;

public abstract class BettingPolicy {

    protected BettingRate bettingRate;

    public BettingPolicy(BettingRate bettingRate) {
        this.bettingRate = bettingRate;
    }

    public abstract boolean isPolicyApplied(Dealer dealer, Player player);

    public abstract BettingRate getBettingRate(Dealer dealer, Player player);

}
