package blackJack.domain.User;

import blackJack.utils.ExeptionMessage;

public class BettingMoney {
    public static final int NO_MONEY = 0;
    private int money;

    public BettingMoney(int money) {
        validatePositive(money);
        this.money = money;
    }

    private void validatePositive(int money){
        if(money <= NO_MONEY){
            throw new IllegalArgumentException(ExeptionMessage.NOT_ENOUGH_BETTING_MONEY);
        }
    }

    public int getMoney() {
        return money;
    }
}
