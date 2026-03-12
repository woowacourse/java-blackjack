package model;

public class Player extends AbstractParticipant {

    private int bettingMoney;

    public Player(String name) {
        super(name);
    }

    public int getMoney() {
        return this.bettingMoney;
    }

    public void setMoney(int bettingMoney) {
        this.bettingMoney = bettingMoney;
    }
}
