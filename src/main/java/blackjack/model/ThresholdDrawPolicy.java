package blackjack.model;

public class ThresholdDrawPolicy implements DealerDrawPolicy {

    private final int threshold;

    public ThresholdDrawPolicy(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean shouldDraw(int score) {
        return score < threshold;
    }
}
