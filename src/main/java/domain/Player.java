package domain;

public class Player extends Participant {

    private static final int UPPER_BOUND_OF_DRAWABLE_SCORE = 21;
    private final Name name;
    private Decision decision;

    public Player(String inputName) {
        super(UPPER_BOUND_OF_DRAWABLE_SCORE);
        this.name = new Name(inputName);
        this.decision = Decision.HIT;
    }

    public void stand() {
        decision = Decision.STAND;
    }

    public boolean isDrawable() {
        return decision == Decision.HIT &&
                hand.calculateScore() < UPPER_BOUND_OF_DRAWABLE_SCORE;
    }
}
