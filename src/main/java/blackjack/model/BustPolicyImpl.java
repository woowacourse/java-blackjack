package blackjack.model;

public class BustPolicyImpl implements BustPolicy {

    private static final int THRESHOLD = 21;

    @Override
    public boolean isBust(int score) {
        return score > THRESHOLD;
    }
}
