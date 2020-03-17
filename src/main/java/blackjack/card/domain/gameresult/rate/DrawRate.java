package blackjack.card.domain.gameresult.rate;

import blackjack.card.domain.CardBundle;

public class DrawRate implements MoneyRate {

    private static final double DRAW_RATE = 0D;

    @Override
    public Double apply(CardBundle cardBundle) {
        return DRAW_RATE;
    }
}
