package domain;

public class Dealer extends Participant {
    private static final int DEALER_PLAYING_THRESHOLD = 16;

    public Dealer(String name, Hand hand) {
        super("딜러", hand);
    }

    @Override
    protected boolean isPlayable() {
        return hand.scoreSum() <= DEALER_PLAYING_THRESHOLD;
    }

    @Override
    public boolean isDealer() {
        return true;
    }
}
