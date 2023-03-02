package domain;

public class Player extends Participant {

    private static final int UPPER_BOUND_OF_DRAWABLE_SCORE = 21;
    private final Name name;

    public Player(String inputName) {
        super(UPPER_BOUND_OF_DRAWABLE_SCORE);
        this.name = new Name(inputName);
    }
}
