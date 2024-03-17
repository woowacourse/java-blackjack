package blackjack.domain.betting;

import static blackjack.domain.betting.GameResult.BLACKJACK;
import static blackjack.domain.betting.GameResult.LOSE;
import static blackjack.domain.betting.GameResult.PUSH;
import static blackjack.domain.betting.GameResult.WIN;

public record Profit(int value) {
    private static final double BLACKJACK_PROFIT_RATE = 1.5;

    public static Profit of(final Money money, final GameResult status) {
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
