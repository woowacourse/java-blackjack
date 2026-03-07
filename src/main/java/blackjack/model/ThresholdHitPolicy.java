package blackjack.model;

public class ThresholdHitPolicy implements DealerHitPolicy {

    private final int threshold;

    public ThresholdHitPolicy(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean shouldHit(int score) {
        return score < threshold;
    }
}
