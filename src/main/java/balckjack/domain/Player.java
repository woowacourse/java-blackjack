package balckjack.domain;

public class Player extends Participant {

    private final Name name;

    public Player(String name) {
        this.name = new Name(name);
    }

    public Name getName() {
        return name;
    }
}
