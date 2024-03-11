package domain;

public class Player extends Gamer {
    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    public boolean isNameOf(String test) {
        return name.name().equals(test);
    }

    public String getName() {
        return name.name();
    }
}
