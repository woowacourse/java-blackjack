package domain;

public class Dealer extends Player {
    public Dealer(final String name) {
        super(name);
    }

    public boolean isUpToThreshold(final int threshold) {
        return this.calculateScore(21) > threshold;
    }
}
