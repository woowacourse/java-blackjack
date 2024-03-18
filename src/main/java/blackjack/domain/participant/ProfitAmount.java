package blackjack.domain.participant;

public class ProfitAmount {

    private final int amount;

    public ProfitAmount(int amount) {
        this.amount = amount;
    }

    public static ProfitAmount from(BetAmount betAmount) {
        return new ProfitAmount(betAmount.getAmount());
    }

    public ProfitAmount add(ProfitAmount other) {
        return new ProfitAmount(amount + other.amount);
    }

    public ProfitAmount inverse() {
        return new ProfitAmount(-amount);
    }

    public ProfitAmount multiplication(double profitRate) {
        return new ProfitAmount((int) (amount * profitRate));
    }

    public int getAmount() {
        return amount;
    }
}
