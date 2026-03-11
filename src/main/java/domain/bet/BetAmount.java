package domain.bet;

public class BetAmount {
    private static final String BET_AMOUNT_UNIT_ERROR_MESSAGE = "베팅금액을 100원단위로 맞춰주세요";
    private final int amount;

    public BetAmount(int amount) {
        validateBetAmountUnit(amount);
        this.amount = amount;
    }

    public int getBetAmount() {
        return amount;
    }

    private void validateBetAmountUnit(int amount){
        if(amount % 100 != 0){
            throw new IllegalArgumentException(BET_AMOUNT_UNIT_ERROR_MESSAGE);
        }
    }
}
