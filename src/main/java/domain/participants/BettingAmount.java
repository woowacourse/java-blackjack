package domain.participants;

public class BettingAmount {
    private final static int MIN_BETTING_AMOUNT = 10000;

    private final int amount;

    public BettingAmount(int amount) {
        validateBettingAmount(amount);
        this.amount = amount;
    }

    private void validateBettingAmount(int amount) {
        if (amount < MIN_BETTING_AMOUNT) {
            throw new IllegalArgumentException("배팅 금액은 최소 10000원 이상이여야합니다.");
        }
    }
}
