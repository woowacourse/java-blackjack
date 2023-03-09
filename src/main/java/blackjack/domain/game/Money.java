package blackjack.domain.game;

public class Money {

    private final int value;

    public Money(final int value) {
        this.value = value;
    }

    public int getProfit(GameResult result) {
        return (int) (result.getTimes() * value);
    }

    public int getValue() {
        return value;
    }
}
