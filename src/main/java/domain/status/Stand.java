package domain.status;

import domain.card.Cards;
import java.math.BigDecimal;

public class Stand extends Status {

    private final Cards cards;

    public Stand(final Cards cards) {
        this.cards = cards;
    }

    public BigDecimal profitWeight() {
        return BigDecimal.valueOf(1);
    }
}
