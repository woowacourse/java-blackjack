package domain.user;

public class Dealer extends Player {

    private static final int DEALER_THRESHOLD = 16;

    public Dealer(String playerName, Hand hand) {
        super(playerName, hand);
    }

    public boolean isHit() {
        return sumCardPool() <= DEALER_THRESHOLD;
    }
}
