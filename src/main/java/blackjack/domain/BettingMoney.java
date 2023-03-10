package blackjack.domain;

public class BettingMoney {

    private static final int MIN_BETTING_MONEY = 0;

    private final int bettingMoney;

    public BettingMoney(int bettingMoney) {
        validateRange(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validateRange(int bettingMoney) {
        if (bettingMoney < MIN_BETTING_MONEY) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 " + MIN_BETTING_MONEY + "원 이상이여야 합니다.");
        }
    }

    public long getBlackjackPrize() {
        return Math.round(bettingMoney * 1.5);
    }

    public long getPrize() {
        return bettingMoney;
    }
}
