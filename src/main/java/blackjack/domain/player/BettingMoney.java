package blackjack.domain.player;

public class BettingMoney {
    private final String MONEY_RANGE_ERROR = "[ERROR] 베팅 금액은 0보다 커야합니다.";
    private final int bettingMoney;

    public BettingMoney(int bettingMoney) {
        validatePositive(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validatePositive(int bettingMoney) {
        if (bettingMoney <= 0) {
            throw new IllegalArgumentException(MONEY_RANGE_ERROR);
        }
    }

    public int getBettingMoney() {
        return bettingMoney;
    }
}
