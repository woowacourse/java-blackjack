package blackjack.domain.result;

import blackjack.domain.card.CardBundle;

class WinRate implements MoneyRate {

    private static final double DEFAULT_WIN_RATE = 1D;
    private static final double BLACKJACK_RATE = 1.5;

    @Override
    public Double apply(CardBundle cardBundle) {
        if (cardBundle.isBlackjack()) {
            return BLACKJACK_RATE;
        }
        return DEFAULT_WIN_RATE;
    }
}
