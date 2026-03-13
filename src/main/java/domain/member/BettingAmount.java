package domain.member;

public class BettingAmount {

    private static final double BONUS_RATE = 1.5;

    private int amount;

    public BettingAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void applyBonus() {
        this.amount = (int) (amount * BONUS_RATE);
    }
}
