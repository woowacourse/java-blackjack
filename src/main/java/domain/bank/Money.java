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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
