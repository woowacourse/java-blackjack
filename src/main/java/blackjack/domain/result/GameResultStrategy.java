package blackjack.domain.result;

import blackjack.domain.score.Score;

abstract class GameResultStrategy {

    public boolean fulfill1(Score dealerScore, Score gamblerScore) {
        boolean enough = enough(dealerScore, gamblerScore);
        if (enough) {
            return true;
        }
        int compare = Integer.compare(gamblerScore.getScore(), dealerScore.getScore());
        return isMatch(compare);
    }

    protected abstract boolean enough(Score dealerScore, Score gamblerScore);

    protected abstract boolean isMatch(int compare);

}
