package blackjack.user;

public class Wallet {

    private static final int PRINCIPAL_MIN_NUMBER = 10000;
    private static final int PRINCIPAL_MAX_NUMBER = 10000000;

    private final int principal;
    private final int profit;

    private Wallet(int principal, int profit) {
        validateRange(principal);

        this.principal = principal;
        this.profit = profit;
    }

    public static Wallet initialBetting(int principal) {
        return new Wallet(principal, 0);
    }

    private void validateRange(int principal) {
        if (principal < PRINCIPAL_MIN_NUMBER || principal > PRINCIPAL_MAX_NUMBER) {
            throw new IllegalArgumentException("원금은 1만원에서 1000만원까지 입력할 수 있습니다.");
        }
    }
}
