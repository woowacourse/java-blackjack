package domain.participant;

import java.util.regex.Pattern;

public class Money {
    private static final String MAX_INT_VALUE = String.valueOf(Integer.MAX_VALUE);
    private static final int MAX_INT_LENGTH = String.valueOf(Integer.MAX_VALUE).length();
    private static final Pattern COUNT_PATTERN = Pattern.compile("\\d+");
    private static final int MINIMUM_AMOUNT = 1;
    private final int amount;

    public Money(String input) {
        validateNumeric(input);
        validateCountRange(input);
        int parsedInput = Integer.parseInt(input);
        validateMinimum(parsedInput);
        validateUnit(parsedInput);
        this.amount = parsedInput;
    }

    private Money(int amount) {
        this.amount = amount;
    }

    public static Money zero() {
        return new Money(0);
    }

    public Money subtract(Money other) {
        return new Money(this.amount - other.amount);
    }

    private void validateUnit(int parsedInput) {
        if (parsedInput % 10 != 0) {
            throw new IllegalArgumentException("베팅 금액은 10원 단위여야 합니다.");
        }
    }

    private void validateMinimum(int parsedInput) {
        if (parsedInput < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException("베팅 금액은 " + MINIMUM_AMOUNT + "원 이상만 입력해주세요.");
        }
    }

    private void validateCountRange(String count) {
        if (count.length() > MAX_INT_LENGTH ||
                (count.length() == MAX_INT_LENGTH && count.compareTo(MAX_INT_VALUE) > 0)) {
            throw new IllegalArgumentException("베팅 금액은 2,147,483,647원 이하로만 입력해주세요.");
        }
    }

    public Money multiply(double rate) {
        return new Money((int) (amount * rate));
    }

    public int getAmount() {
        return amount;
    }

    private void validateNumeric(String input) {
        if (!COUNT_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("숫자가 아닙니다.");
        }
    }
}
