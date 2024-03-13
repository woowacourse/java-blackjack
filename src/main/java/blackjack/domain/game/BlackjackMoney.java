package blackjack.domain.game;

public class BlackjackMoney {

    public static final double BlackjackMultiple = 1.5;
    private final int amount;

    public BlackjackMoney(int amount) {
        this.amount = amount;
    }

    public BlackjackMoney toNegative() {
        return new BlackjackMoney(-amount);
    }

    public BlackjackMoney applyBlackjackMultiple() {
        return new BlackjackMoney((int) (amount * BlackjackMultiple));
    }

    public int getAmount() {
        return amount;
    }
}
