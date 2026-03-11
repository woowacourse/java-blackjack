package model;

public class Player extends AbstractParticipant {

    private int money;

    public Player(String name) {
        super(name);
    }

    public void setMoney(int bettingMoney) {
        this.money = bettingMoney;
    }
}
