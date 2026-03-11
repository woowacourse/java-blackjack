package domain.participant;

public class Money {
    private final int bettingMoney;
    private static final String NEGATIVE_BETTING_MONEY_MESSAGE = "[ERROR] 배팅금은 0보다 커야 합니다!";
    private static final int MIN_BETTING_MONEY = 0;

    public Money(int bettingMoney) {
        validatePositiveMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    public int getBettingMoney() {
        return bettingMoney;
    }

    private void validatePositiveMoney(int bettingMoney) {
        if (bettingMoney < MIN_BETTING_MONEY) {
            throw new IllegalArgumentException(NEGATIVE_BETTING_MONEY_MESSAGE);
        }
    }
}
