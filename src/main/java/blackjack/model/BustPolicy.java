package blackjack.model;

public class BustPolicy {

    private static final int THRESHOLD = 21;

    public boolean isBust(int score) {
        return score > 21;
    }
}
