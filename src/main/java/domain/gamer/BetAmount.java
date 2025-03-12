package domain.gamer;

public class BetAmount {

    private final int amount;

    public BetAmount(final int amount) {
        validateMaxAndMinAmount(amount);
        this.amount = amount;
    }

    private void validateMaxAndMinAmount(final int amount) {
        if (amount < 1000 || amount > 100000) {
            throw new IllegalArgumentException("배팅 금액은 1,000원 이상, 100,000원 이하여야 합니다.");
        }
    }
}
