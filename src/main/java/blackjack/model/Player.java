package blackjack.model;

public class Player extends Participant {

    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    public String getName() {
        return name.get();
    }
}
