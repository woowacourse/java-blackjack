package domain.betting.policy;

import domain.betting.BettingRate;
import domain.gamer.Dealer;
import domain.gamer.Player;

public class BlackjackPolicy extends BettingPolicy{

    public BlackjackPolicy() {
        super(BettingRate.BLACK_JACK);
    }

    @Override
    public boolean isPolicyApplied(Dealer dealer, Player player) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    @Override
    public BettingRate getBettingRate(Dealer dealer, Player player) {
        return bettingRate;
    }

}
