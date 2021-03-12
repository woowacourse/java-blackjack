package blackjack.domain.state;

import blackjack.domain.user.Money;

public abstract class Running implements State {
    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public double calculateProfit(Money money) {
        throw new IllegalStateException("아직 최종 수익을 구할 수 없습니다.");
    }
}
