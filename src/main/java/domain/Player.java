package domain;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    @Override
    boolean isDrawable() {
        Score totalScore = hand.calculateCardSum();
        return !hand.isBust(totalScore);
    }
}
