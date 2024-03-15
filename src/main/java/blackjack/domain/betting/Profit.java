package blackjack.domain.betting;

import blackjack.domain.cardgame.WinningStatus;

import static blackjack.domain.cardgame.WinningStatus.BLACKJACK;
import static blackjack.domain.cardgame.WinningStatus.LOSE;
import static blackjack.domain.cardgame.WinningStatus.PUSH;
import static blackjack.domain.cardgame.WinningStatus.WIN;

public record Profit(int value) {
    private static final double BLACKJACK_PROFIT_RATE = 1.5;

    public static Profit of(final Money money, final WinningStatus status) {
        if (WIN.equals(status)) {
            return new Profit(money.value());
        }
        if (LOSE.equals(status)) {
            return new Profit(money.minusValue());
        }
        if (PUSH.equals(status)) {
            return new Profit(0);
        }
        if (BLACKJACK.equals(status)) {
            return new Profit(money.multipleValue(BLACKJACK_PROFIT_RATE));
        }

        throw new IllegalStateException();
    }
}
