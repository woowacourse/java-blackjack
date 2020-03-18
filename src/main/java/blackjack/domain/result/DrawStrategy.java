package blackjack.domain.result;

import blackjack.domain.score.Score;

class DrawStrategy extends GameResultStrategy {

    private static final int DRAW_VALUE = 0;

    @Override
    protected boolean enough(Score gamblerScore, Score dealerScore) {
        return gamblerScore.isBlackjack() && dealerScore.isBlackjack();
    }

    @Override
    protected boolean isMatch(int compare) {
        return DRAW_VALUE == compare;
    }
}
