package team.blackjack.domain;

public class Player extends Participant {
    private final String name;
    private double batMoney;

    public Player(String name) {
        this.name = name;

    }

    public String getName() {
        return this.name;
    }

    public void bat(double money) {
        this.batMoney = money;
    }

    public double getBatMoney() {
        return this.batMoney;
    }
}
