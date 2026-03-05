package domain;

public class Player extends Participant {

    protected Player(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    protected boolean isPlaying() {
        return false;
    }
}
