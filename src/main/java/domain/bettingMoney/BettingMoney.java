package domain.bettingMoney;

public class BettingMoney {
    private final int value;

    public BettingMoney(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        BettingMoney other = (BettingMoney) obj;
        return value == other.value;
    }
}
