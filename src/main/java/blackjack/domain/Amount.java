package blackjack.domain;

public class Amount {

    private static final long MAX_BETTING_AMOUNT = Long.MAX_VALUE / 5 * 2;
    private final long value;

    private Amount(long value) {
        validate(value);
        this.value = value;
    }

    public static Amount from(String rawAmount) {
        long amount = validateParse(rawAmount);
        return new Amount(amount);
    }

    public long getValue() {
        return value;
    }

    private void validate(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0보다 큰 양수여야 합니다.");
        }
        if (amount > MAX_BETTING_AMOUNT) {
            throw new IllegalArgumentException("베팅 금액의 최댓값을 초과한 금액입니다.");
        }
    }

    private static long validateParse(String rawAmount) {
        long amount;
        try {
            amount = Long.parseLong(rawAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("금액은 숫자가 입력되어야 합니다.");
        }
        return amount;
    }
}
