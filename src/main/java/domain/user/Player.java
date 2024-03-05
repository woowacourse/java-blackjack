package domain.user;

public class Player extends User {
    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }
}
