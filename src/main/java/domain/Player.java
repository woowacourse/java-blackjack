package domain;

public class Player extends Participant {

    private static final int PLAYING_VALUE = 20;

    public Player(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    protected boolean isPlaying() {
        return hand.scoreSum() <= PLAYING_VALUE;
    }
}
