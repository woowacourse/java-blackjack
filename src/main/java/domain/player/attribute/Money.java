package domain.player.attribute;

public class Money {

    private final long betAmount;

    public Money(String betAmountInput) {
        this.betAmount = validateBetAmount(betAmountInput);
    }

    public long getBetAmount() {
        return this.betAmount;
    }

    private long validateBetAmount(String betAmountInput) {
        long betAmount = validateNumericAmount(betAmountInput);
        validateThousandUnit(betAmount);
        validateBetAmountIsPositive(betAmount);
        validateAmountDoesNotExceedMaxAmount(betAmount);

        return betAmount;
    }

    private long validateNumericAmount(String betAmountInput) {
        try {
            return Long.parseLong(betAmountInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("베팅 금액은 정수여야 한다.");
        }
    }

    private void validateThousandUnit(long betAmount) {
        if (betAmount % 1000 != 0) {
            throw new IllegalArgumentException("베팅 금액은 1000원 단위여야 합니다.");
        }
    }

    private void validateBetAmountIsPositive(long betAmount) {
        if (betAmount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 양수여야 한다");
        }
    }

    private void validateAmountDoesNotExceedMaxAmount(long betAmount) {
        if (betAmount > 10_000_000_000L) {
            throw new IllegalArgumentException("베팅 금액은 100억을 넘지 않는다");
        }
    }
}
