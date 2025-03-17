package game;

public class Betting {

    private static final double BLACK_JACK_ODDS = 1.5;

    private final int betting;

    public Betting(int money) {
        validatePositive(money);
        this.betting = money;
    }

    private void validatePositive(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("[ERROR] 올바른 베팅 금액을 입력해주세요.");
        }
    }

    public int evaluate(GameResult result) {
        return switch (result) {
            case BLACKJACK -> blackJack();
            case WIN -> earn();
            case DRAW -> same();
            case LOSE -> lose();
        };
    }

    private int blackJack() {
        return (int) (betting * BLACK_JACK_ODDS);
    }

    private int earn() {
        return betting;
    }

    private int same() {
        return 0;
    }

    private int lose() {
        return betting * -1;
    }

    public int getBetting() {
        return betting;
    }
}
