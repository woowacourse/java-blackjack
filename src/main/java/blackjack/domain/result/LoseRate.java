package blackjack.domain.result;

import blackjack.domain.card.CardBundle;

class LoseRate implements MoneyRate {

    private static final double LOSE_RATE = -1D;

    @Override
    public Double apply(CardBundle cardBundle) {
        return LOSE_RATE;
    }
}
