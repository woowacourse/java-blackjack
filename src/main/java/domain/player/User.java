package domain.player;

public class User extends Player {
    public User(String name) {
        super(name);
    }

    @Override
    public void openInitialCards() {
        openCards(2);
    }
}
