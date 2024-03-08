package domain;

public class Dealer extends Player {
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isUpToThreshold(final int threshold) {
        return this.calculateResultScore() > threshold;
    }

}
