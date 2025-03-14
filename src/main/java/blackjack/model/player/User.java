package blackjack.model.player;

public class User extends Player {

    public User(final String name) {
        super(name);
    }

    @Override
    public boolean isDealer() {
        return false;
    }

}
