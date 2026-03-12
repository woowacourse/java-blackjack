package domain;

public class Money {
    private final int money;

    public Money(int money) {
        validateRange(money);
        this.money = money;
    }

    private void validateRange(int money) {
        if (money < 1000 || money > 300000) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 1,000원 이상 300,000원 이하로 입력해주세요.");
        }
    }
}
