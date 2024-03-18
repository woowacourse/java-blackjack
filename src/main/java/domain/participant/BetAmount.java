package domain.participant;

public class BetAmount {
    public static final int MINIMUM_BET_AMOUNT = 100;
    private final int betAmount;

    public BetAmount(final int betAmount) {
        validate(betAmount);
        this.betAmount = betAmount;
    }

    private static void validate(final int betAmount) {
        if (betAmount < MINIMUM_BET_AMOUNT) {
            throw new IllegalArgumentException("배팅 금액은 100원 이상만 가능합니다.");
        }
    }

    public int toInt() {
        return betAmount;
    }
}
