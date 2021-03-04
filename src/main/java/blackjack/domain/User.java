package blackjack.domain;

public class User extends Participant {
    private static final int STANDARD = 21;

    private final Name name;

    public User(String name) {
        this(new Name(name));
    }

    public User(Name name) {
        this.name = name;
    }

    public String getName() {
        return this.name.getName();
    }

    @Override
    public boolean isDrawable() {
        return cards.calculateTotalValue() <= STANDARD;
    }
}
