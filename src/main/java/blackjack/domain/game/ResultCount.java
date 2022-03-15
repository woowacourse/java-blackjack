package blackjack.domain.game;

public class ResultCount {

    private int value = 0;

    public int toInt() {
        return value;
    }

    public void increment() {
        value++;
    }

    @Override
    public String toString() {
        return "ResultCount{" + "value=" + value + '}';
    }
}
