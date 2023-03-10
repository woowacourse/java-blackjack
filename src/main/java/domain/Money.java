package domain;

public class Money {

    public static final int MINIMUM_BETTING_MONEY = 1;

    private final int money;

    public Money(int money) {
        validateNonPositiveNumber();
        this.money = money;
    }

    private void validateNonPositiveNumber() {
        if (money < MINIMUM_BETTING_MONEY) {
            throw new IllegalArgumentException("[ERROR] 음수 또는 0을 입력할 수 없습니다.");
        }
    }
}
