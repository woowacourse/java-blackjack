package domain.user;

public class Dealer extends Player {

    private static final int DEALER_HIT_LIMIT = 16;

    public Dealer(String playerName, Hand hand) {
        super(playerName, hand);
    }

    public boolean canHit() {
        return sumHand() <= DEALER_HIT_LIMIT;
    }
}
