package blackJack.domain.User;

import blackJack.utils.ExeptionMessage;

import java.util.Objects;

public class BettingMoney {
    public static final int NO_MONEY = 0;
    private int money;

    public BettingMoney(int money, boolean isDealer) {
        validatePositive(money, isDealer);
        this.money = money;
    }

    private void validatePositive(int money, boolean isDealer){
        if(money <= NO_MONEY && !isDealer){
            throw new IllegalArgumentException(ExeptionMessage.NOT_ENOUGH_BETTING_MONEY);
        }
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BettingMoney that = (BettingMoney) o;
        return money == that.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
