package blackjack.model;

public class DealerThresholdHitPolicy implements DealerHitPolicy {

    private final int threshold;

    public DealerThresholdHitPolicy(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean shouldHit(Score score) {
        return score.value() < threshold;
    }
}
