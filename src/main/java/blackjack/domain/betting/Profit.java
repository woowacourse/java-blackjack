package blackjack.domain.betting;

import blackjack.domain.cardgame.GameResult;

import static blackjack.domain.cardgame.GameResult.BLACKJACK;
import static blackjack.domain.cardgame.GameResult.LOSE;
import static blackjack.domain.cardgame.GameResult.PUSH;
import static blackjack.domain.cardgame.GameResult.WIN;

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
