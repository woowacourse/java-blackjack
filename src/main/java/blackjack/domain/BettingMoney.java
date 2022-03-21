package blackjack.domain;

public class BettingMoney {
    private static final String NEGATIVE_MONEY_ERROR_MESSAGE = "베팅 금액은 음수를 입력할 수 없습니다.";
    private static final int MONEY_LEAST_UNIT = 100;
    private static final String MULTIPLES_OF_MONEY_LEAST_ERROR_MESSAGE
            = String.format("베팅 금액은 %d의 배수로 입력해주세요", MONEY_LEAST_UNIT);
    private final int amount;

    public BettingMoney(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException(NEGATIVE_MONEY_ERROR_MESSAGE);
        }
        if (isNotMultiplesOfMoneyLeast(money)) {
            throw new IllegalArgumentException(MULTIPLES_OF_MONEY_LEAST_ERROR_MESSAGE);
        }
    }

    private boolean isNotMultiplesOfMoneyLeast(int money) {
        return money % MONEY_LEAST_UNIT != 0;
    }

    public int getAmount() {
        return amount;
    }
}
