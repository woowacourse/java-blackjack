package domain.player.attribute;

public class Money {

    private final Long betAmount;

    public Money(String betAmountInput) {
        this.betAmount = validateBetAmount(betAmountInput);
    }

    public Long getBetAmount() {
        return this.betAmount;
    }

    private Long validateBetAmount(String betAmountInput) {
        Long betAmount = validateNumericAmount(betAmountInput);
        validateBetAmountIsPositive(betAmount);
        validateAmountDoesNotExceedMaxAmount(betAmount);

        return betAmount;
    }

    private Long validateNumericAmount(String betAmountInput) {
        try {
            return Long.parseLong(betAmountInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("베팅 금액은 정수여야 한다.");
        }
    }

    private void validateBetAmountIsPositive(Long betAmount) {
        if (betAmount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 양수여야 한다");
        }
    }

    private void validateAmountDoesNotExceedMaxAmount(Long betAmount) {
        if (betAmount > 10_000_000_000L) {
            throw new IllegalArgumentException("베팅 금액은 100억을 넘지 않는다");
        }
    }
}
