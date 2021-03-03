package blackjack.domain.player;

public class User extends AbstractPlayer {
    public User() {
        super();
    }

    public User(String name) {
        super(name);
    }

    @Override
    public boolean isCanDraw() {
        return getValue() <= BLACKJACK;
    }
}
