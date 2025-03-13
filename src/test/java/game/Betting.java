package game;

public class Betting {

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
        return this.money;
    }

    public int earnedWhenBlackJack() {
        return (int) (this.money * 1.5);
    }
}
