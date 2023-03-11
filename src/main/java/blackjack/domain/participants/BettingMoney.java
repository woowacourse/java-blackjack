package blackjack.domain.participants;

public class BettingMoney {

    public static final int MIN = 1000;

    private final int amount;

    public BettingMoney(final int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(final int amount) {
        if (amount < MIN) {
            throw new IllegalArgumentException("베팅 금액은 1000원 이상이어야 합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
