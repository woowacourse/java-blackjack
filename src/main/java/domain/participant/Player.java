package domain.participant;


import domain.hand.Hand;

public class Player extends Participant {
    private final Name name;
    private final BettingMoney bettingMoney;

    private Player(Name name, Hand hand, BettingMoney bettingMoney) {
        super(hand);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public static Player of(Name name, Hand hand, BettingMoney bettingMoney) {
        return new Player(name, hand, bettingMoney);
    }

    public Name getName() {
        return name;
    }

    public String getNameValue() {
        return name.getName();
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }
}
