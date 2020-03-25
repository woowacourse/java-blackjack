package domain.gamer;

import exception.InvalidMoneyException;

import java.util.Objects;

public class Money {
    private static final Money ZERO_MONEY = new Money(0);

    private int money;

    public Money(String inputMoney) {
        try {
            this.money = Integer.parseInt(inputMoney);
        } catch (NumberFormatException e) {
            throw new InvalidMoneyException();
        }

        validBettingMoney(money);
    }

    private Money(int money) {
        this.money = money;
    }

    private void validBettingMoney(int money) {
        if (money < 0) {
            throw new InvalidMoneyException();
        }
    }

    public Money reversion() {
        return new Money(-money);
    }

    public Money getZeroMoney() {
        return ZERO_MONEY;
    }

    public Money sum(Money other) {
        return new Money(money + other.money);
    }

    public Money multiply(double operand) {
        return new Money((int) (money * operand));
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
