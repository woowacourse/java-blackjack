package blackJack.domain.result;

public class BettingAmount {

    public static final String NOT_POSITIVE_NUMBER_ERROR = "베팅 금액은 0 이상이어야 합니다.";

    public static final int DEFAULT_BETTING_AMOUNT = 0;
    public static final int MINIMUM_BETTING_AMOUNT = 1;

    private final int bettingAmount;

    private BettingAmount(int bettingAmount) {
        this.bettingAmount = bettingAmount;
    }

    public static BettingAmount newByDefault() {
        return new BettingAmount(DEFAULT_BETTING_AMOUNT);
    }

    public BettingAmount startBetting(int bettingAmount) {
        validatePositiveNumber(bettingAmount);
        return new BettingAmount(bettingAmount);
    }

    private void validatePositiveNumber(int bettingAmount) {
        if (bettingAmount < MINIMUM_BETTING_AMOUNT) {
            throw new IllegalArgumentException(NOT_POSITIVE_NUMBER_ERROR);
        }
    }

    public int calculateProfit(BlackJackMatch blackJackMatch) {
        return blackJackMatch.calculateProfit(bettingAmount);
    }
}
