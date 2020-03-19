package blackjack.domain.playing.user;

public class Player extends AbstractUser {
    private Player(String name) {
        super(name, Cards.emptyCards());
    }

    public static Player of(String name) {
        return new Player(name);
    }
}
