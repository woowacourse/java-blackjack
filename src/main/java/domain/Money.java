package domain;

public class Money {
    private double money;

    public Money(double money) {
        if (money <= 0.0) {
            throw new IllegalArgumentException("배팅 금액은 1원 이상 가능합니다.");
        }
        this.money = money;
    }

    public double getMoney() {
        return money;
    }
}
