package model;

public class Player extends AbstractParticipant {

    private BettingMoney bettingMoney;

    public Player(String name) {
        super(name);
    }

    public void setMoney(BettingMoney bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public long bettingMoney() {
        return this.bettingMoney.bettingMoney();
    }
}
