package domain.participant;

import java.util.regex.Pattern;

public class Money {
    private static final Pattern COUNT_PATTERN = Pattern.compile("\\d+");
    private final int amount;

    public Money(String input) {
        validateNumeric(input);
        this.amount = Integer.parseInt(input);
    }

    private Money(int amount) {
        this.amount = amount;
    }

    public Money multiply(double rate) {
        return new Money((int) (amount * rate));
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }

    private void validateNumeric(String input) {
        if (!COUNT_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("숫자가 아닙니다.");
        }
    }
}
