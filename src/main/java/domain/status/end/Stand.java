package domain.status.end;

import domain.card.Cards;
import java.math.BigDecimal;

public final class Stand extends EndStatus {

    private final Cards cards;

    public Stand(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public BigDecimal profitWeight() {
        return BigDecimal.valueOf(1);
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
