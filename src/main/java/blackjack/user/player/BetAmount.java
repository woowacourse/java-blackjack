package blackjack.user.player;

public class BetAmount {

    private static final int PRINCIPAL_MIN_NUMBER = 10_000;
    private static final int PRINCIPAL_MAX_NUMBER = 10_000_000;
    private static final int INITIAL_PRINCIPAL = 10_000;
    private static final int INITIAL_PROFIT = 0;

    private final int principal;
    private final int profit;

    public BetAmount(final int principal, final int profit) {
        validateRange(principal);

        this.principal = principal;
        this.profit = profit;
    }

    public static BetAmount initAmount() {
        return new BetAmount(INITIAL_PRINCIPAL, INITIAL_PROFIT);
    }

    public static BetAmount initAmountWithPrincipal(final int principal) {
        return new BetAmount(principal, INITIAL_PROFIT);
    }

    private void validateRange(final int principal) {
        if (principal < PRINCIPAL_MIN_NUMBER || principal > PRINCIPAL_MAX_NUMBER) {
            throw new IllegalArgumentException("원금은 1만원에서 1000만원까지 입력할 수 있습니다.");
        }
    }

    public int getPrincipal() {
        return principal;
    }

    public int getProfit() {
        return profit;
    }
}
