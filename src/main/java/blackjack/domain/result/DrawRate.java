package blackjack.domain.result;

import blackjack.domain.card.CardBundle;

class DrawRate implements MoneyRate {

    private static final double DRAW_RATE = 0D;

    @Override
    public Double apply(CardBundle cardBundle) {
        return DRAW_RATE;
    }
}
