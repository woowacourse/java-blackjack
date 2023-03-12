package domain.participants;

import domain.BettingMoney;

public class Player extends Participant {

    private final BettingMoney bettingMoney;

    public Player(String name, BettingMoney money) {
        super(new Name(name));
        this.bettingMoney = money;
    }

    public int getMoney() {
        return bettingMoney.getBettingMoney();
    }

}

