package blackjack.model;

public class BustPolicyImpl implements BustPolicy {

    private final int bustThreshold;

    public BustPolicyImpl(int bustThreshold) {
        this.bustThreshold = bustThreshold;
    }

    @Override
    public boolean isBust(Score score) {
        return score.value() > bustThreshold;
    }
}
