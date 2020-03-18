package blackjack.domain.result;

import blackjack.domain.score.Score;

class LoseStrategy extends GameResultStrategy {

    private static final int LOSE_VALUE = -1;

    @Override
    protected boolean enough(Score dealerScore, Score gamblerScore) {
        return isOnlyGamblerBurst(gamblerScore, dealerScore) || isOnlyDealerBlackjack(gamblerScore, dealerScore);
    }

    private boolean isOnlyDealerBlackjack(Score gamblerScore, Score dealerScore) {
        return dealerScore.isBlackjack() && !gamblerScore.isBlackjack();
    }

    private boolean isOnlyGamblerBurst(Score gamblerScore, Score dealerScore) {
        return gamblerScore.isBurst() && !dealerScore.isBurst();
    }

    @Override
    protected boolean isMatch(int compare) {
        return LOSE_VALUE == compare;
    }
}
