package blackjack.domain.user;

public class Player extends User {
    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player create(String name) {
        return new Player(name);
    }

    @Override
    public int getHitLimit() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }
}
