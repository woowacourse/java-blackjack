package blackjack.domain.state;

import blackjack.domain.card.Cards;

import java.math.BigDecimal;

public class BlackJack extends Finished {
    public static final int BLACKJACK_NUMBER = 21;
    public static final BigDecimal BLACKJACK_RATE = new BigDecimal("1.5");

    public BlackJack(Cards cards) {
        super(cards);
    }

    @Override
    public BigDecimal rate() {
        return BLACKJACK_RATE;
    }

    @Override
    public boolean isBlackJack() {
        return true;
    }

    @Override
    public boolean isStay() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
