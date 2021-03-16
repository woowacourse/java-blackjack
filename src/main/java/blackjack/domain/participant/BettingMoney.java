package blackjack.domain.participant;

public class BettingMoney {
    public static final String MONEY_NOT_POSITIVE_ERROR_MESSAGE = "금액은 양수여야 합니다.";
    public static final int MINIMUM_BETTING_MONEY = 1;

    private final int bettingMoney;

    public BettingMoney(int value) {
        validateBettingMoneyRange(value);
        this.bettingMoney = value;
    }

    private void validateBettingMoneyRange(int value) {
        if (value < MINIMUM_BETTING_MONEY) {
            throw new IllegalArgumentException(MONEY_NOT_POSITIVE_ERROR_MESSAGE);
        }
    }

    public int getBettingMoney() {
        return bettingMoney;
    }
}
