package domain;

public final class Player extends Participant {
    private final Name name;

    public Player(Name name) {
        super();
        this.name = name;
    }

    public Player(String name) {
        this(new Name(name));
    }

    public String getName() {
        return name.getName();
    }
}
