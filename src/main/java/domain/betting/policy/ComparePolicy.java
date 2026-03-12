package domain.betting.policy;

import domain.analyzer.BettingResult;
import domain.betting.BettingRate;
import domain.gamer.Dealer;
import domain.gamer.Player;

public class ComparePolicy extends BettingPolicy{
    public ComparePolicy() {
        super(BettingResult.COMPARE_WIN);
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
            return bettingResult.bettingRate();
        }
        BettingResult lose = bettingResult.reverseResult();
        return lose.bettingRate();
    }
}
