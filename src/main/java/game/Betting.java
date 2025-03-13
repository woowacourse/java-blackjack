package game;

public class Betting {

    private static final int DEFAULT_ODDS = 1;
    private static final double BLACK_JACK_ODDS = 1.5;

    private int money;

    public Betting(int money) {
        this.money = money;
    }

    public int evaluate(GameResult result) {
        return switch (result) {
            case WIN -> earn();
            case LOSE -> lose();
            case DRAW -> same();
            case BLACKJACK -> earnedWhenBlackJack();
        };
    }

    private int lose() {
        return this.money * -1;
    }

    private int same() {
        return 0;
    }

    private int earn() {
        return this.money * DEFAULT_ODDS;
    }

    private int earnedWhenBlackJack() {
        return (int) (this.money * BLACK_JACK_ODDS);
    }

    public int getMoney() {
        return money;
    }
}
