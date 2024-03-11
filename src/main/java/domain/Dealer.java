package domain;

public class Dealer extends Player {
    private static final int THRESHOLD = 16;

    public Dealer() {
        super();
    }

    public Dealer(final Hand hand) {
        super(hand);
    }

    public boolean isUpToThreshold() {
        return this.calculateResultScore() > THRESHOLD;
    }

}
