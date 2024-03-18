package blackjack.domain.betting;

public class BettingMoney {
    private final int moeney;

    public BettingMoney(final int money) {
        this.moeney = money;
    }

    public BettingMoney multiply(final double betMultiplier) {
        return new BettingMoney((int) (this.moeney * betMultiplier));
    }

    public int getMoney() {
        return moeney;
    }
}
