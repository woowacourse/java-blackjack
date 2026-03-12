package domain.betiing;

public record BetAmount(int amount) {

    private static final int MINIMUM_BET_AMOUNT = 0;

    public static BetAmount from(int amount) {
        validatePositive(amount);
        return new BetAmount(amount);
    }

    private static void validatePositive(int amount) {
        if (amount < MINIMUM_BET_AMOUNT) {
            throw new IllegalArgumentException("배팅금액은 " + MINIMUM_BET_AMOUNT + " 이상이어야 합니다.");
        }
    }

}
