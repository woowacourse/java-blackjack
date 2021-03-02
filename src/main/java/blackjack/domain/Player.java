package blackjack.domain;

public class Player extends User {
    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player create(String name) {
        return new Player(name);
    }

    @Override
    String getName() {
        return name;
    }
}
