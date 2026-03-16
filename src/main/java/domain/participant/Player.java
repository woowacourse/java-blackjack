package domain.participant;

public class Player extends Participant {

    private final Name name;

    public Player(String name) {
        super();
        this.name = new Name(name);
    }

    public Name getName() {
        return name;
    }

    @Override
    public boolean canDraw() {
        return !isBust();
    }
}
