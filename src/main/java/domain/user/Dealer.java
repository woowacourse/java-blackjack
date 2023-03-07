package domain.user;

public class Dealer extends Player {

    private static final int DEALER_THRESHOLD = 16;

    public Dealer(final String playerName, final CardPool cardPool) {
        super(playerName, cardPool);
    }

    public boolean needsHit() {
        return sumCardPool() <= DEALER_THRESHOLD;
    }
}
