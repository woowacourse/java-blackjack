package domain.betting.policy;

import domain.analyzer.BettingResult;
import domain.betting.BettingRate;
import domain.gamer.Dealer;
import domain.gamer.Player;

public class ComparePolicy extends BettingPolicy{

    public ComparePolicy() {
        super(BettingResult.COMPARE_WIN.bettingRate());
    }

    @Override
    public boolean isPolicyApplied(Dealer dealer, Player player) {
        return player.isStop() && dealer.isStop();
    }

    @Override
    public BettingRate getBettingRate(Dealer dealer, Player player) {
        return compareScore(dealer.getResultScore(), player.getResultScore());
    }

    private BettingRate compareScore(int dealerScore, int playerScore) {
        if (playerScore > dealerScore) {
            return this.bettingRate;
        }
        return this.bettingRate.reverseBettingRate();
    }

}
