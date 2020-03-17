package domain;

public class Money {
    private int money;

    public Money(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("배팅 금액은 1원 이상 가능합니다.");
        }
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
