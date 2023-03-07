package domain.model;

public class Player extends Participant {

    private final String name;

    public Player(final Cards cards, final String name) {
        super(cards);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
