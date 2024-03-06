package domain;

public class Player extends Gamer {
    private final Name name;

    public Player(final Name name) {
        this.name = name;
    }

    @Override
    public void hit(final Decks decks) {
        hand.add(decks.draw());
    }

    public Name getName() {
        return name;
    }
}
