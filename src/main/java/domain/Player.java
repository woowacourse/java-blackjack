package domain;

public class Player extends Participant {

    private final String name;

    private Player(String name, Cards cards) {
        super(cards);
        this.name = name;
    }

    public static Player init(String name) {
        return new Player(name, Cards.empty());
    }

    public static Player from(String name, Cards cards) {
        return new Player(name, cards);
    }
}
