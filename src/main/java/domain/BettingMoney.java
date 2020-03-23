package domain;

public class BettingMoney {
    private static final int MIN_BETTING_MONEY = 1;

    private int bettingMoney;

    public BettingMoney(int bettingMoney) {
        if (bettingMoney < MIN_BETTING_MONEY) {
            throw new IllegalArgumentException("배팅 금액은 1원 이상 가능합니다.");
        }

        this.bettingMoney = bettingMoney;
    }

    public int getBettingMoney() {
        return bettingMoney;
    }
}
