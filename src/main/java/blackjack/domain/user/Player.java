package blackjack.domain.user;

public class Player extends User {
    private Player(String name) {
        super(name);
    }

    public static Player of(String name) {
        return new Player(name);
    }
}
