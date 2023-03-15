package domain.status.end;

import domain.card.Cards;
import java.math.BigDecimal;

public final class BlackJack extends EndStatus {

    private final Cards cards;

    public BlackJack(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public BigDecimal profitWeight() {
        return BigDecimal.valueOf(1.5);
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
