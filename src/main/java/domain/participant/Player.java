package domain.participant;

import domain.batting.Money;

public class Player extends Participant {

    private Money money;

    public Player(String name) {
        super(name);
    }

    public void battingMoney(Money money) {
        this.money = money;
    }

    public Money getBattingMoney() {
        return money;
    }
}
