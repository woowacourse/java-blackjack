package domain;

public class Dealer extends Player {
    private static final String NAME = "딜러";
    private static final int BOUNDARY = 16;

    public Dealer(BlackjackHand blackjackHand) {
        super(NAME, blackjackHand);
    }

    public boolean needsToHit() {
        return super.getBlackjackHand().getTotalScore() <= BOUNDARY;
    }

}
