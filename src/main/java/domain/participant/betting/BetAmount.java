package domain.participant.betting;

public class BetAmount {
    private static final int MIN_BET_AMOUNT = 1;

    private final int betAmount;

    public BetAmount(int betAmount) {
        validateMinBetAmount(betAmount);
        this.betAmount = betAmount;
    }

    private void validateMinBetAmount(int betAmount) {
        if (betAmount < MIN_BET_AMOUNT) {
            throw new IllegalArgumentException(
                    String.format("rejected value: %d - 최소 배팅 금액은 $%d입니다.", betAmount, MIN_BET_AMOUNT)
            );
        }
    }

    public int getValue() {
        return this.betAmount;
    }
}
