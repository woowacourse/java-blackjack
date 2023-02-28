package domain;

public class Dealer {
    private static final String NAME = "딜러";
    private final Name name;

    public Dealer() {
        this.name = new Name(NAME);
    }

    public String getName() {
        return name.getName();
    }

}
