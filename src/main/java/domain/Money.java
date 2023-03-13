package domain;

public class Money {

    public static final int MINIMUM_BETTING_MONEY = 0;

    private final int money;

    public Money(int money) {
        validateNonPositiveNumber(money);
        this.money = money;
    }

    private void validateNonPositiveNumber(int money) {
        if (money < MINIMUM_BETTING_MONEY) {
            throw new IllegalArgumentException("[ERROR] 음수는 입력할 수 없습니다.");
        }
    }

    public int getMoney() {
        return money;
    }
}
