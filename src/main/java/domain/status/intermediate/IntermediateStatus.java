package domain.status.intermediate;

import domain.card.Card;
import domain.card.Cards;
import domain.status.Status;
import java.math.BigDecimal;

public abstract class IntermediateStatus implements Status {
    @Override
    public Status initialDraw(final Cards cards) {
        throw new UnsupportedOperationException("초기 상태에서만 가능합니다.");
    }

    @Override
    public abstract Status draw(final Card card);

    @Override
    public abstract Status selectStand();

    @Override
    public BigDecimal profitWeight() {
        throw new UnsupportedOperationException("현재 상태에서는 수익 가중치를 계산할 수 없습니다.");
    }

}
