package domain.user;

import domain.result.PrizeRatio;

public class BettingMoney {

    private static final int CRITERIA = 0;

    private final int money;

    public BettingMoney(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money <= CRITERIA) {
            throw new IllegalArgumentException("배팀금액은 0원 이하일 수 없습니다.");
        }
    }

    public int calculateProfit(PrizeRatio ratio) {
        return (int) (money * ratio.getRatio());
    }
}
