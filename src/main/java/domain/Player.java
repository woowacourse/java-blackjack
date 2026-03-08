package domain;


public class Player extends Participant{
    private final Name name;

    private Player(Name name, Hand hand) {
        super(hand);
        this.name = name;
    }

    public static Player of(Name name, Hand hand) {
        return new Player(name, hand);
    }

    public Name getName() {
        return name;
    }
}
