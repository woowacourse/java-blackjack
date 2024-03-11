package domain;

public class Dealer extends Player {
    private static final int THRESHOLD = 16;

    public Dealer() {
        super();
    }

    public boolean isUpToThreshold() {
        return this.calculateResultScore() > THRESHOLD;
    }

}
