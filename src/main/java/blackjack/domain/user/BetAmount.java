package blackjack.domain.user;

public class BetAmount {

    private static final int MINIMUM_AMOUNT = 500;
    private static final int MAXIMUM_AMOUNT = 1_000_000;
    private final int amount;

    public BetAmount(int amount) {
        validateAmountRange(amount);
        this.amount = amount;
    }

    private void validateAmountRange(int amount) {
        if (amount < MINIMUM_AMOUNT || amount > MAXIMUM_AMOUNT) {
            throw new IllegalArgumentException(
                    String.format("입력 가능한 배팅 금액은 최소 %d원 이상 %d원 이하입니다.", MINIMUM_AMOUNT, MAXIMUM_AMOUNT)
            );
        }
    }
}
