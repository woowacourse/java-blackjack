package blackjack.domain.participant;

public class PlayerMoney {

    private final double amount;

    public PlayerMoney(double amount) {
        this.amount = amount;
    }

    public PlayerMoney multiply(double multiplier) {
        return new PlayerMoney(amount * multiplier);
    }

    public boolean isGreaterEqualThan(double target) {
        return amount >= target;
    }

    public double getAmount() {
        return amount;
    }
}
