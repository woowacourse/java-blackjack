package model;

public class Player extends AbstractParticipant {

    private long bettingMoney;

    public Player(String name) {
        super(name);
    }

    public long money() {
        return this.bettingMoney;
    }

    public void setMoney(long bettingMoney) {
        this.bettingMoney = bettingMoney;
    }
}
