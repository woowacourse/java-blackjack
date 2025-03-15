package blackjack.domain.state.finished;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import blackjack.domain.state.State;
import blackjack.domain.winning.WinningResult;

public class Stay extends Finished {
    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    protected WinningResult calculateWinningResult(State state) {
        if (state instanceof Bust) {
            return WinningResult.WIN;
        }
        if (state instanceof Blackjack) {
            return WinningResult.LOSE;
        }
        return compareScore(state);
    }

    @Override
    protected double earningsRate() {
        return 2;
    }

    private WinningResult compareScore(State state) {
        Score myScore = calculateTotalScore();
        Score otherScore = state.calculateTotalScore();
        if (myScore.compareTo(otherScore) > 0) {
            return WinningResult.WIN;
        }
        if (myScore.compareTo(otherScore) < 0) {
            return WinningResult.LOSE;
        }
        return WinningResult.DRAW;
    }
}
