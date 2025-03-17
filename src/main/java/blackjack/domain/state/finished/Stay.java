package blackjack.domain.state.finished;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import blackjack.domain.winning.WinningResult;

public class Stay extends Finished {
    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public WinningResult decide(Cards dealerCards) {
        if (dealerCards.isBust()) {
            return WinningResult.WIN;
        }
        if (dealerCards.isBlackjack()) {
            return WinningResult.LOSE;
        }
        return compareScore(dealerCards);
    }

    @Override
    protected double earningsRate() {
        return 2;
    }

    private WinningResult compareScore(Cards dealerCards) {
        Score myScore = calculateTotalScore();
        Score otherScore = dealerCards.calculateMaxScore();
        if (myScore.compareTo(otherScore) > 0) {
            return WinningResult.WIN;
        }
        if (myScore.compareTo(otherScore) < 0) {
            return WinningResult.LOSE;
        }
        return WinningResult.DRAW;
    }
}
