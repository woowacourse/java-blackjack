package blackjack.card.domain.gameresult.rate;

import blackjack.card.domain.CardBundle;

public class LoseRate implements MoneyRate {

    private static final double LOSE_RATE = -1D;

    @Override
    public Double apply(CardBundle cardBundle) {
        return LOSE_RATE;
    }
}
