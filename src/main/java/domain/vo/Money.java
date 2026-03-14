package domain.vo;

public class Money {
    private static final int MIN_INCREMENT = 10_000;
    private final int money;

    public Money(String input) {
        int value = validateAndParse(input);
        validateMinIncrement(value);
        this.money = value;
    }

    public Money(int input) {
        this.money = input;
    }

    public Money(double input) {
        int value = (int) (input);
        this.money = value;
    }

    private int validateAndParse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] " + MIN_INCREMENT + "원 이상의 정수를 입력해야 합니다.");
        }
    }

    private void validateMinIncrement(int value) {
        if (value < MIN_INCREMENT || value % MIN_INCREMENT != 0){
            throw new IllegalArgumentException("[ERROR] " + MIN_INCREMENT+ "원 단위여야 하며, 최소 금액은 " + MIN_INCREMENT + "입니다.");
        }
    }

    public int getValueOf() {
        return Integer.valueOf(money);
    }
}
