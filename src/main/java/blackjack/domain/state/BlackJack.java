package blackjack.domain.state;

import blackjack.domain.card.Cards;

import java.math.BigDecimal;

public class BlackJack extends Finish {
    public BlackJack(final Cards cards) {
        super(cards);
    }

    @Override
    public BigDecimal getEarningRates(State dealerState) {
        if (dealerState.cards().isBlackJack()) {
            return new BigDecimal("0");
        }
        return new BigDecimal("1.5");
    }
}
