package blackjack.domain.user;

public class Player extends User {
    private Player(String name) {
        super(name, Cards.emptyCards());
    }

    public static Player of(String name) {
        return new Player(name);
    }
}
