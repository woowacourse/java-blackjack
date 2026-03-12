package blackjack.domain.betting;

public record BettingAmount(
        int amount
) {

    private static final int MIN_AMOUNT_RANGE = 1_000;
    private static final int MAX_AMOUNT_RANGE = 1_000_000;

    public BettingAmount {
        validateAmountRange(amount);
    }

    private void validateAmountRange(int amount) {
        if (MIN_AMOUNT_RANGE > amount || amount > MAX_AMOUNT_RANGE) {
            throw new IllegalArgumentException("베팅 금액은 최소 1,000원 ~ 최대 1,000,000원 입니다.");
        }
    }

}
