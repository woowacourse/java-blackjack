package game;

public class Betting {

    private static final int DEFAULT_ODDS = 1;
    private static final double BLACK_JACK_ODDS = 1.5;

    private int money;

    public Betting(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void lose() {
        this.money = 0;
    }

    public int earn() {
        return this.money * DEFAULT_ODDS;
    }

    public int earnedWhenBlackJack() {
        return (int) (this.money * BLACK_JACK_ODDS);
    }
}
