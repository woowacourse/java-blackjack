package blackjack.domain.player;

public class BetAmount {
    private static final int minBetAmount = 0;

    private final int amount;

    public BetAmount(String amount) {
        this(Integer.parseInt(amount));
    }

    public BetAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount <= minBetAmount) {
            throw new IllegalArgumentException("배팅금액은 0원보다 커야합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
