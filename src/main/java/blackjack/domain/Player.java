package blackjack.domain;

public class Player {
    private final Name name;

    public Player(final String name) {
        this.name = new Name(name);
    }

    public String getName() {
        return name.getValue();
    }
}
