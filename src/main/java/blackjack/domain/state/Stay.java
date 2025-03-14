package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import blackjack.domain.winning.WinningResult;

public class Stay extends Start {
    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("더이상 카드를 뽑을 수 없습니다.");
    }

    @Override
    public State stay() {
        return this;
    }

    @Override
    public double profit(double bettingMoney) {
        return bettingMoney;
    }

    @Override
    public Score calculateTotalScore() {
        return cards.calculateMaxScore();
    }

    @Override
    public WinningResult decide(State state) {
        if (!state.isFinished()) {
            throw new IllegalArgumentException("끝난 상태와 승패를 결정할 수 있습니다.");
        }
        if (state instanceof Bust) {
            return WinningResult.WIN;
        }
        if (state instanceof Blackjack) {
            return WinningResult.WIN;
        }
        return compareScore(state);
    }

    @Override
    public boolean isFinished() {
        return true;
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
