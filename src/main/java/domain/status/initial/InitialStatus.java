package domain.status.initial;

import domain.card.Card;
import domain.card.Cards;
import domain.status.Status;
import java.math.BigDecimal;

public abstract class InitialStatus implements Status {
    @Override
    public abstract Status initialDraw(final Cards cards);

    @Override
    public Status draw(final Card card) {
        throw new UnsupportedOperationException("현재 상태에서는 카드를 뽑을 수 없습니다.");
    }

    @Override
    public Status selectStand() {
        throw new UnsupportedOperationException("현재 상태에서는 Stand를 선택할 수 없습니다.");
    }

    @Override
    public BigDecimal profitWeight() {
        throw new UnsupportedOperationException("현재 상태에서는 수익 가중치를 계산할 수 없습니다.");
    }
}
