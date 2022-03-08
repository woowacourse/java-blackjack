package blackjack.domain;

public abstract class Player {
    private final String name;

    protected Player(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
