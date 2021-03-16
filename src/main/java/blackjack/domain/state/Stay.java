package blackjack.domain.state;

import blackjack.domain.card.Cards;

import java.math.BigDecimal;

public class Stay extends Finish {
    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public BigDecimal getEarningRates(State dealerState) {
        if (dealerState.calculateScore() > cards().calculateScore() && !dealerState.cards().isBust()) {
            return new BigDecimal("-1");
        }

        if (dealerState.calculateScore() == cards().calculateScore()) {
            return new BigDecimal("0");
        }

        return new BigDecimal("1");
    }
}
