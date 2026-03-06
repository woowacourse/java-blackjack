package domain;

public class Dealer extends Participant{

    private static final int PLAYING_VALUE = 16;

    public Dealer(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    protected boolean isPlayable() {
        return hand.scoreSum() <= PLAYING_VALUE;
    }
}
