package blackjack.domain;

public class BettingMoney {
    private final int value;

    public BettingMoney(int value) {
        this.value = value;
    }

    public int intValue() {
        return value;
    }

}
