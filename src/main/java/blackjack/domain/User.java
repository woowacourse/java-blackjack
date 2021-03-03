package blackjack.domain;

public class User extends Participant {
    private static final int STANDARD = 21;

    private final String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public boolean isDrawable() {
        return calculateTotalValue() < STANDARD;
    }

    @Override
    public void draw() {

    }
}
