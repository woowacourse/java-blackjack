package domain;

public class Dealer {
    private static final String NAME = "딜러";
    private static final int BOUNDARY = 16;
    private final BlackjackHand blackjackHand;

    public Dealer(BlackjackHand blackjackHand) {
        this.blackjackHand = blackjackHand;
    }

    public BlackjackHand getBlackjackHand() {
        return blackjackHand;
    }

    public boolean needsToHit() {
        return blackjackHand.getTotalScore() <= BOUNDARY;
    }

}
