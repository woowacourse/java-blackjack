package blackjack.bettingMachine;

public class Money {

    private static final int DOUBLE_RATE = 2;
    private static final int TRIPLE_RATE = 3;
    private long money;

    public Money(final long money) {
        this.money = money;
    }

    public void increase(final long amount) {
        this.money += amount;
    }

    public long getDouble() {
        return money * DOUBLE_RATE;
    }

    public long getMoney() {
        return money;
    }

    public long getOneAndHalf() {
        return money * TRIPLE_RATE / DOUBLE_RATE;
    }
}
