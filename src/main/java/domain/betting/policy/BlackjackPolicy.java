package domain.betting.policy;

import domain.analyzer.BettingResult;
import domain.betting.BettingRate;
import domain.gamer.Dealer;
import domain.gamer.Player;

public class BlackjackPolicy extends BettingPolicy{
    public BlackjackPolicy() {
        super(BettingResult.BLACK_JACK);
    }

    @Override
    public boolean isPolicyApplied(Dealer dealer, Player player) {
        return player.isBlackjack() && (dealer.isBusted() || dealer.isStop());
    }

    @Override
    public BettingRate getBettingRate(Dealer dealer, Player player) {
        return bettingResult.bettingRate();
    }
}
