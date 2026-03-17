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
        if (money <= 0) {
            throw new IllegalArgumentException("배팅 금액은 0보다 커야 합니다.");
        }

        this.batMoney = money;
    }

    public double getBatMoney() {
        return this.batMoney;
    }
}
