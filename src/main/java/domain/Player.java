package domain;

public class Player extends Gamer {
    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    @Override
    public boolean canHit() {
        return !this.isBust();
    }

    public boolean hasName(Name comparedName) {
        return name.equals(comparedName);
    }

    public Name getName() {
        return name;
    }
}
