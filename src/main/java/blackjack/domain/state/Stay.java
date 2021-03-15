package blackjack.domain.state;

import blackjack.domain.card.Cards;

import java.math.BigDecimal;

public class Stay extends Finished {

    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public BigDecimal rate() {
        return BigDecimal.ONE;
    }

    @Override
    public boolean isBlackJack() {
        return false;
    }

    @Override
    public boolean isStay() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
