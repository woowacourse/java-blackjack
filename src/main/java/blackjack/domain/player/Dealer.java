package blackjack.domain.player;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int HIT_THRESHOLD = 16;

    public Dealer(Hand hand) {
        super(new PlayerName(DEALER_NAME), hand);
    }

    @Override
    public boolean canHit() {
        return hand.calculateCardSummation() <= HIT_THRESHOLD;
    }
}
