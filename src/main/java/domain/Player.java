package domain;

public class Player extends BlackjackParticipant {

    public Player(String name) {
        super(name);
    }

    @Override
    boolean isDrawable() {
        return !hand.isBust();
    }
}
