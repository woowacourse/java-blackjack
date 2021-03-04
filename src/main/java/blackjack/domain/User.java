package blackjack.domain;

public class User extends Participant {
    private static final int STANDARD = 21;

    public User(String name) {
        super(new Name(name));
    }

    @Override
    public boolean isDrawable() {
        return cards.calculateTotalValue() <= STANDARD;
    }
}
