package domain.bank;

import java.util.Objects;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/10
 */
public class Money {

    protected final int money;

    public Money(final int money) {
        this.money = money;
    }

    public Money calculateMoneyByProfit(final double profit) {
        return new Money((int) (money * profit));
    }

    @Override
    public boolean equals(final Object otherMoney) {
        if (this == otherMoney) {
            return true;
        }
        if (otherMoney == null || getClass() != otherMoney.getClass()) {
            return false;
        }
        final Money another = (Money) otherMoney;
        return money == another.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    public int getMoney() {
        return this.money;
    }
}
