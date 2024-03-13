package domain;

public class Dealer extends Player {
    private static final int THRESHOLD = 16;

    public Dealer() {
        super();
    }

    public Dealer(final Hand hand) {
        super(hand);
    }

    public boolean isNotUpToThreshold() {
        return this.calculateResultScore() <= THRESHOLD;
    }
}
