package blackjackgame.domain;

public class Dealer extends Player {
    private static final int DEALER_HIT_STANDARD = 16;
    private static final String DEALER = "딜러";

    public Dealer(final Hand hand) {
        super(hand);
    }

    public boolean canHit() {
        return getScore() <= DEALER_HIT_STANDARD;
    }

    public String getName() {
        return DEALER;
    }
}
