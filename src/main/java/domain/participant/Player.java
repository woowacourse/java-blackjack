package domain.participant;

import domain.money.Money;
import util.Validator;

public class Player extends Participant {

    private final String name;
    private final Money money;

    public Player(String name, Integer money) {
        Validator.validateNameLength(name);
        Validator.validateNameEng(name);
        this.name = name;
        this.money = new Money(money);
    }

    public String getName() {
        return name;
    }

    public Money getMoney() {
        return money;
    }
}
