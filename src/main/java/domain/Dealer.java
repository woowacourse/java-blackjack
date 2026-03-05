package domain;

public class Dealer extends Participant{

    private static final int PLAYING_VALUE = 16;

    protected Dealer(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    protected boolean isPlaying() {
        return hand.scoreSum() <= PLAYING_VALUE;
    }
}
