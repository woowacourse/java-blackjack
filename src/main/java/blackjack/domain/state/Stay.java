package blackjack.domain.state;

import blackjack.domain.card.Cards;

import java.math.BigDecimal;

public class Stay extends Finish {
    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public BigDecimal getEarningRates(State state) {
        return new BigDecimal("-1");
    }
}
