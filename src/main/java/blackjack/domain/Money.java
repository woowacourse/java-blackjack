package blackjack.domain;

public class Money {

    private final int bettingMoney;

    public Money(int bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public int getBettingMoney() {
        return bettingMoney;
    }
}
