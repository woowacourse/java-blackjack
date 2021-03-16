package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;

public abstract class Finished extends Started {
    public Finished(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Cards cards) {
        throw new IllegalStateException("종료된 상태에서는 카드를 더 받을 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("종료된 상태에서는 스테이 상태로 변경될 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Money profit(Money money, Dealer dealer) {
        return new Money(money.getMoney() * profitRate(dealer));
    }

    public abstract double profitRate(Dealer dealer);
}
