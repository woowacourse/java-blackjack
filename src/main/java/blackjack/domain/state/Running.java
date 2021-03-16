package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;

public abstract class Running extends Started {
    public Running(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Money profit(Money money, Dealer dealer) {
        throw new IllegalStateException("실행중인 상태에서는 최종 수익을 구할 수 없습니다.");
    }

    @Override
    public double profitRate(Dealer dealer) {
        throw new IllegalStateException("실행중인 상태에서는 수익률을 구할 수 없습니다.");
    }
}
