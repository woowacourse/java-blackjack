package domain;

public class Amount {
    private static final int MAX_AMOUNT = 100_000_000;

    private final int amount;

    public Amount(final String value) {
        int parsedValue = validateParse(value);
        validateAmount(parsedValue);
        this.amount = parsedValue;
    }

    private int validateParse(final String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "%s는 올바른 배팅 금액이 아닙니다. 배팅 금액은 %d원 이하의 양의 정수만 가능합니다.", value, MAX_AMOUNT));
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(
                    "%s는 올바른 배팅 금액이 아닙니다. 배팅 금액은 %d원 이하의 양의 정수만 가능합니다.", value, MAX_AMOUNT));
        }
    }

    private void validateAmount(final int parsedValue) {
        if (parsedValue <= 0 || parsedValue > MAX_AMOUNT) {
            throw new IllegalArgumentException(String.format(
                    "%d는 올바른 배팅 금액이 아닙니다. 배팅 금액은 %d원 이하의 양의 정수만 가능합니다.", parsedValue, MAX_AMOUNT));
        }
    }

    public int getAmount() {
        return amount;
    }
}
