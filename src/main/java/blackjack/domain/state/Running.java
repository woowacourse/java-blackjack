package blackjack.domain.state;

import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.vo.BettingMoney;

public abstract class Running implements State {

    private final HoldCards holdCards;

    Running(HoldCards holdCards) {
        this.holdCards = holdCards;
    }

    public HoldCards holdCards() {
        return holdCards;
    }

    @Override
    public final State blackjack() {
        if (holdCards().isBlackjack()) {
            return new Blackjack(holdCards());
        }
        return new Hit(holdCards());
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public final double profit(BettingMoney bettingMoney, Dealer dealer) {
        throw new IllegalStateException("Running 상태에서 수입을 확인할 수 없습니다.");
    }

    @Override
    public final int score() {
        throw  new IllegalStateException("Running 상태에서 점수를 확인할 수 없습니다.");
    }

    @Override
    public final HoldCards getHoldCards() {
        return holdCards;
    }
}
