package domain;

public class Player extends Participant {

    public Player(String name) {
        super(new Name(name));
    }

    public boolean isBust() {
        return this.getState() == ScoreState.BUST;
    }
}
