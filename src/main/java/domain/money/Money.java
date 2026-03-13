package domain.money;

public class Money {

    private Integer money;

    public Money(Integer money) {
        this.money = money;
    }

    public Integer applyBlackjack() {
        this.money = (int) (this.money * 1.5);
        return this.money;
    }
}
