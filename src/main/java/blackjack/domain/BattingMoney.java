package blackjack.domain;

public class BattingMoney {
    private final int value;

    public BattingMoney(int value) {
        this.value = value;
    }

    public int intValue() {
        return value;
    }

    public int getBlackjackWinningMoney() {
        return (int) (value * 1.5);
    }

    public int getNormalWinningMoney() {
        return value;
    }

    public int getLosingMoney() {
        return value;
    }
}
