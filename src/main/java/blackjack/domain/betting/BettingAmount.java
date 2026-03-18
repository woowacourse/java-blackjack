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
            throw new IllegalArgumentException(
                    String.format("베팅 금액은 최소 %d원 ~ 최대 %d원 입니다.", MIN_AMOUNT_RANGE, MAX_AMOUNT_RANGE));
        }
    }

}
