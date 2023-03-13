package domain.status.end;

import domain.card.Card;
import domain.card.Cards;
import domain.status.Status;
import java.math.BigDecimal;

public abstract class EndStatus implements Status {
    @Override
    public final Status initialDraw(final Cards cards) {
        throw new UnsupportedOperationException("초기 상태에서만 가능합니다.");
    }

    @Override
    public final Status draw(final Card card) {
        throw new UnsupportedOperationException("더이상 카드를 뽑을 수 없습니다.");
    }

    @Override
    public final Status selectStand() {
        throw new UnsupportedOperationException("상태가 정의되어 더이상 실행할 수 없습니다.");
    }

    @Override
    public abstract BigDecimal profitWeight();
}
