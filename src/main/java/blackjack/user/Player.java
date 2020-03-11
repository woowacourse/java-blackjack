package blackjack.user;

public class Player extends AbstractUser {
    private Player(String name) {
        super(name);
    }

    public static Player of(String name) {
        return new Player(name);
    }
}
