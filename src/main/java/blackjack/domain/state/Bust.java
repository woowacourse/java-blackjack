package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import blackjack.domain.winning.WinningResult;

public class Bust extends Start {
    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("더 이상 카드를 받을 수 없습니다.");
    }

    @Override
    public State stay() {
        return this;
    }

    @Override
    public double profit(double bettingMoney) {
        return -bettingMoney;
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
        return WinningResult.LOSE;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
