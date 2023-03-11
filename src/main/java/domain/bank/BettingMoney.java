package domain.bank;

import domain.bank.Money;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/11
 */
public class BettingMoney extends Money {
    public BettingMoney(final int money) {
        super(money);
        validate(money);
    }

    private void validate(final int money) {
        if (money < 100) {
            throw new IllegalArgumentException("최소 베팅 금액은 100원 입니다.");
        }
        if (money % 100 != 0) {
            throw new IllegalArgumentException("100원 단위로 입력가능합니다.");
        }
        if (money > 100_000_000) {
            throw new IllegalArgumentException("최대 베팅 금액은 1억원 입니다.");
        }
    }
}
