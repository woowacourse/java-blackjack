package model;

public class BettingMoney {
    private static final long MIN_BETTING_MONEY = 100;
    private static final long MAX_BETTING_MONEY = 1000000000;
    private static final long BETTING_UNIT = 100;
    private final long bettingMoney;

    public BettingMoney(long bettingMoney) {
        validate(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    public long bettingMoney() {
        return bettingMoney;
    }

    private void validate(long amount) {
        if (amount < MIN_BETTING_MONEY || amount > MAX_BETTING_MONEY) {
            throw new IllegalArgumentException("베팅 금액은 100 이상 10억 이하만 가능합니다.");
        }
        if (amount % BETTING_UNIT != 0) {
            throw new IllegalArgumentException("베팅 금액은 100원 단위여야 합니다.");
        }
    }
}
