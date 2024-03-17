package blackjack.model.betting;

public class BetAmount {

    private static final int MIN_BET_AMOUNT = 100;
    private static final int BET_AMOUNT_UNIT = 10;

    private final int betAmount;

    public BetAmount(int betAmount) {
        validateMinimumBetAmount(betAmount);
        validateBetAmountUnit(betAmount);
        this.betAmount = betAmount;
    }

    private void validateMinimumBetAmount(int betAmount) {
        if (betAmount < MIN_BET_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 배팅은 100원부터 가능합니다.");
        }
    }

    private void validateBetAmountUnit(int betAmount) {
        if (betAmount % BET_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 배팅은 10원 단위로만 가능합니다.");
        }
    }

    public int getBetAmount() {
        return betAmount;
    }
}
