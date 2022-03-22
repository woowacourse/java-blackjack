package domain.betting;

public class BettingMoney {

    private static final String KEEP_BETTING_MONEY_POSITIVE_ERROR_MESSAGE_FORMAT = "[Error] \"%d\" : 베팅 금액은 0 이하가 될 수 없습니다.";

    private final int bettingMoney;

    public BettingMoney(int bettingMoney) {
        validateMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validateMoney(int bettingMoney) {
        if (bettingMoney <= 0) {
            throw new IllegalArgumentException(
                    String.format(KEEP_BETTING_MONEY_POSITIVE_ERROR_MESSAGE_FORMAT, bettingMoney)
            );
        }
    }

    public int getBettingMoney() {
        return bettingMoney;
    }
}
