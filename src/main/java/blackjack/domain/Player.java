package blackjack.domain;

public class Player {
    private final Name name;

    public Player(final String name) {
        this.name = Name.from(name);
    }

    public String getName() {
        return name.getValue();
    }
}
