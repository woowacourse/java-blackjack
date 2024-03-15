package blackjack.domain.game;

public class BlackjackMoney {

    public static final int NEGATIVE_MULTIPLE = -1;

    private final int amount;

    public BlackjackMoney(int amount) {
        this.amount = amount;
    }

    public BlackjackMoney toNegative() {
        return applyMultiple(NEGATIVE_MULTIPLE);
    }

    public BlackjackMoney applyMultiple(double multiple) {
        return new BlackjackMoney((int) (amount * multiple));
    }

    public BlackjackMoney add(BlackjackMoney money) {
        return new BlackjackMoney(amount + money.amount);
    }

    public int getAmount() {
        return amount;
    }
}
