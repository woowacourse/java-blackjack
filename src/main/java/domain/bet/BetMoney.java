package domain.bet;

public class BetMoney {

    private static final int BET_AMOUNT_UNIT = 1000;
    private static final String INVALID_UNIT_BET_AMOUNT_INPUT = "베팅 금액은 1000원 단위여야 합니다.";

    private final int amount;

    public BetMoney(final int amount) {
        validateBetAmount(amount);
        this.amount = amount;
    }

    private static void validateBetAmount(final double parsedBetAmount) {
        if(parsedBetAmount % BET_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException(INVALID_UNIT_BET_AMOUNT_INPUT);
        }
    }

    public int getAmount() {
        return amount;
    }

}
