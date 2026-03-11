package domain.participant;

import domain.batting.Money;

public class Player extends Participant {

    private Money money;

    public Player(String name) {
        super(name);
    }

    public Player(String name, HandCards handCards) {
        super(name, handCards);
    }

    public void battingMoney(Money money) {
        this.money = money;
    }

    public Money getBattingMoney() {
        return money;
    }
}
