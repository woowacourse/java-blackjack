package domain.user;

public class Bet {

    private static final int MIN_BET_AMOUNT = 1;

    private static final String BET_AMOUNT_SMALL_MESSAGE =
            String.format("[ERROR] 배팅 금액은 %d이상이어야 합니다.", MIN_BET_AMOUNT);

    private final int credit;

    public Bet(final int credit) {
        validate(credit);
        this.credit = credit;
    }

    private void validate(final int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(BET_AMOUNT_SMALL_MESSAGE);
        }
    }

    public int getCredit() {
        return credit;
    }
}
