package blackjack.domain;

public class Gambler implements Player {
    private final Name name;

    public Gambler(final String name) {
        this.name = Name.from(name);
    }

    public String getName() {
        return name.getValue();
    }
}
