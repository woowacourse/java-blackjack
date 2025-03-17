package blackjack.domain.state.finished;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.Started;
import blackjack.domain.state.State;
import blackjack.domain.winning.WinningResult;

public abstract class Finished extends Started {
    protected Finished(Cards cards) {
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
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profit(Cards dealerCards, double bettingMoney) {
        WinningResult winningResult = decide(dealerCards);
        if (winningResult == WinningResult.WIN) {
            return bettingMoney * earningsRate() - bettingMoney;
        }
        if (winningResult == WinningResult.DRAW) {
            return 0;
        }
        return -bettingMoney;
    }

    abstract protected WinningResult decide(Cards dealerCards);

    abstract protected double earningsRate();
}
