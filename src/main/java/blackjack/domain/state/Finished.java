package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.BettingMoney;

public abstract class Finished implements State {

    private final HoldCards holdCards;

    Finished(HoldCards holdCards) {
        this.holdCards = holdCards;
    }

    @Override
    public final State draw(Card card) {
        throw new IllegalStateException("Finish 상태에서 카드를 뽑을 수 없습니다.");
    }

    @Override
    public final State stay() {
        throw new IllegalStateException("Finish 상태에서 Stay 할 수 없습니다.");
    }

    @Override
    public final State blackjack() {
        throw new IllegalStateException("Finish 상태에서 블랙잭을 확인할 수 없습니다.");
    }

    @Override
    public final boolean isFinished() {
        return true;
    }

    @Override
    public final double profit(BettingMoney money) {
        return earningRate() * money.getAmount();
    }

    protected abstract double earningRate();

    @Override
    public final HoldCards getHoldCards() {
        return holdCards;
    }
}
