package domain.money;

public class Money {

    private final Integer money;

    public Money(Integer money) {
        validate(money);
        this.money = money;
    }

    public Integer getValue() {
        return money;
    }

    private void validate(Integer money) {
        if (money < 0) {
            throw new IllegalArgumentException("돈은 음수가 될 수 없습니다.");
        }
    }
}
