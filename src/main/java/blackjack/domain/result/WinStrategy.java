package blackjack.domain.result;

import blackjack.domain.score.Score;

class WinStrategy extends GameResultStrategy {

    private static final int WIN_VALUE = 1;

    @Override
    protected boolean enough(Score dealerScore, Score gamblerScore) {
        return dealerScore.isBurst() || isOnlyGamblerBlackjack(dealerScore, gamblerScore);
    }

    private boolean isOnlyGamblerBlackjack(Score dealerScore, Score gamblerScore) {
        return gamblerScore.isBlackjack() && !dealerScore.isBlackjack();
    }

    @Override
    protected boolean isMatch(int compare) {
        return WIN_VALUE == compare;
    }
}
