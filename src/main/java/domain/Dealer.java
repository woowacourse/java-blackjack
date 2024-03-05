package domain;

public class Dealer extends Player {

    private static final int MAX_SCORE_TO_HIT = 16;

    public Dealer(Hand hand) {
        super(new Name("딜러"), hand);
    }

    public boolean canHit() {
        return this.getTotalScore() <= MAX_SCORE_TO_HIT;
    }
}
