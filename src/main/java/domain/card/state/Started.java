package domain.card.state;

import domain.card.Cards;

public abstract class Started extends Initial {
    protected Started(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public int calculateProfit(int bettingAmount) {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " 상태에서는 수익을 계산할 수 없습니다.");
    }
}
