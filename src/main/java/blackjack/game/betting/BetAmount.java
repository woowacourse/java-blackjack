package blackjack.game.betting;

import blackjack.game.GameResult;

public class BetAmount {

    private static final int PRINCIPAL_MIN_NUMBER = 10_000;
    private static final int PRINCIPAL_MAX_NUMBER = 10_000_000;
    private static final int DEFAULT_PROFIT = 0;
    private static final double BLACKJACK_PROFIT_RATE = 1.5;

    private final int principal;
    private final int profit;

    private BetAmount(final int principal, final int profit) {
        validateRange(principal);

        this.principal = principal;
        this.profit = profit;
    }

    public static BetAmount initialBetting(final int principal) {
        return new BetAmount(principal, DEFAULT_PROFIT);
    }

    private void validateRange(final int principal) {
        if (principal < PRINCIPAL_MIN_NUMBER || principal > PRINCIPAL_MAX_NUMBER) {
            throw new IllegalArgumentException("원금은 1만원에서 1000만원까지 입력할 수 있습니다.");
        }
    }

    public BetAmount calculateProfit(final GameResult gameResult, final boolean isBlackjack) {
        if (gameResult.isWin() && isBlackjack) {
            return new BetAmount(principal, (int) (principal * BLACKJACK_PROFIT_RATE));
        }
        if (gameResult.isWin()) {
            return new BetAmount(principal, principal);
        }
        if (gameResult.isLose()) {
            return new BetAmount(principal, -principal);
        }
        return new BetAmount(principal, DEFAULT_PROFIT);
    }

    public int getProfit() {
        return profit;
    }
}
