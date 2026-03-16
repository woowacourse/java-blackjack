package domain.money;

import util.Validator;

public class Money {

    private final Integer money;

    public Money(Integer money) {
        Validator.validateMoney(money);
        this.money = money;
    }

    public Integer getValue() {
        return money;
    }
}
