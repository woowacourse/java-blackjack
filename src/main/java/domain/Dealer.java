package domain;

public class Dealer extends Player {

    public Dealer() {
        super();
    }

    public boolean isUpToThreshold(final int threshold) {
        return this.calculateResultScore() > threshold;
    }

}
