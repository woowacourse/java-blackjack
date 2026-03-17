package domain.money;

public class Money {

    private final Integer money;

    public Money(Integer money) {
        validateMoney(money);
        this.money = money;
    }

    public Integer getValue() {
        return money;
    }

    private void validateMoney(Integer money) {
        if (money < 0) {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요.");
        }
    }
}
