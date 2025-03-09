package model;


public final class Player extends Participant {
    private final String name;

    public Player(final String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
