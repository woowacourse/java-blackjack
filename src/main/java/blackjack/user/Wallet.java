package blackjack.user;

import blackjack.game.GameResult;

public class Wallet {

    private static final int PRINCIPAL_MIN_NUMBER = 10000;
    private static final int PRINCIPAL_MAX_NUMBER = 10000000;
    private static final int DEFAULT_PROFIT = 0;
    private static final double BLACKJACK_PROFIT_RATE = 1.5;

    private final int principal;
    private final int profit;

    private Wallet(final int principal, final int profit) {
        validateRange(principal);

        this.principal = principal;
        this.profit = profit;
    }

    private void validateRange(final int principal) {
        if (principal < PRINCIPAL_MIN_NUMBER || principal > PRINCIPAL_MAX_NUMBER) {
            throw new IllegalArgumentException("원금은 1만원에서 1000만원까지 입력할 수 있습니다.");
        }
    }

    public static Wallet initialBetting(final int principal) {
        return new Wallet(principal, DEFAULT_PROFIT);
    }

    public Wallet calculateProfit(final GameResult gameResult, final boolean isBlackjack) {
        if (gameResult.isWin() && isBlackjack) {
            return new Wallet(principal, (int) (principal * BLACKJACK_PROFIT_RATE));
        }
        if (gameResult.isWin()) {
            return new Wallet(principal, principal);
        }
        if (gameResult.isLose()) {
            return new Wallet(principal, -principal);
        }
        return new Wallet(principal, DEFAULT_PROFIT);
    }

    public int getProfit() {
        return profit;
    }
}
