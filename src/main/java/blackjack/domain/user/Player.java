package blackjack.domain.user;

public class Player extends AbstractUser {
    private Player(String name) {
        super(name);
    }

    static Player of(String name) {
        return new Player(name);
    }
}
