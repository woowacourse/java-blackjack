package domain.game;

public class Bet {
    private final double amount;

    public Bet(double amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 0보다 커야 합니다.");
        }
    }

    public double getAmount() {
        return amount;
    }
}
