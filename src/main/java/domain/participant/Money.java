package domain.participant;

import domain.blackJack.MatchResult;

public class Money {
    private static final int MIN_MONEY_RANGE = 0;

    private int money;

    private Money(int money) {
        validateRange(money);
        this.money = money;
    }

    private void validateRange(int money) {
        if (money <= MIN_MONEY_RANGE) {
            throw new IllegalArgumentException("[ERROR] 알맞은 금액의 범위를 입력해주세요");
        }
    }

    public static Money from(int money) {
        return new Money(money);
    }

    public int calculateProfit(MatchResult matchResult) {
        return (int) Math.floor(money * matchResult.getRate()) - money;
    }
}
