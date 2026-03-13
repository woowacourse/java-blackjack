package domain.bet;

public record Money(
    int value
) {

    public static Money from(String input) {
        validate(input);
        return new Money(Integer.parseInt(input));
    }

    private static void validate(String input) {
        validateMoneyIsNumber(input);
        validateMoneyOutOfRange(input);
        validateMoneyUnit(Integer.parseInt(input));
    }

    private static void validateMoneyIsNumber(String input) {
        if (!isNumber(input)) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 숫자여야 합니다.");
        }
    }

    private static void validateMoneyOutOfRange(String input) {
        int money;
        try {
            money = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 0원 초과 100000000원 이하여야 합니다.");
        }
        if (!(0 < money && money <= 100_000_000)) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 0원 초과 100000000원 이하여야 합니다.");
        }
    }

    private static void validateMoneyUnit(int money) {
        if (money % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액의 단위는 10원 입니다.");
        }
    }

    private static boolean isNumber(String input) {
        for (char number : input.toCharArray()) {
            if (!Character.isDigit(number)) {
                return false;
            }
        }
        return true;
    }
}
