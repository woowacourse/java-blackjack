package blackjack.domain;

public class Player extends Participant {
    private final String name;

    public Player(String name) {
        super(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
