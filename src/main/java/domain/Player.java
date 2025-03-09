package domain;

public class Player extends BlackjackParticipant {

    public Player(String name) {
        super(name);
    }

    @Override
    boolean isDrawable() {
        int sum = calculateCardSum();
        return !isBust(sum);
    }
}
