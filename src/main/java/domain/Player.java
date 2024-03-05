package domain;

public class Player extends Participant {

    private final Name name;

    public Player(Name name, PlayerCards cards) {
        super(cards);
        this.name = name;
    }
}
