package domain;

import java.util.Objects;

public class ResultMoney implements Money {
    private final int money;

    public ResultMoney(int money) {
        this.money = money;
    }

    @Override
    public ResultMoney negative() {
        return new ResultMoney(-money);
    }

    @Override
    public ResultMoney multiply(double number) {
        return new ResultMoney((int) (money * number));
    }

    @Override
    public Money stay() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResultMoney resultMoney1 = (ResultMoney) o;
        return money == resultMoney1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    @Override
    public int getMoney() {
        return money;
    }

}
