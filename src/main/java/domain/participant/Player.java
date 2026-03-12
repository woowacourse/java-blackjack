package domain.participant;

public class Player extends Participant {
    private final Name name;

    public Player(String name) {
        super();
        this.name = new Name(name);
    }

    public String getName() {
        return name.name();
    }
}
