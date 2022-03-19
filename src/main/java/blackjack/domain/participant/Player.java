package blackjack.domain.participant;

public class Player extends Participant {

    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    @Override
    public Name getName() {
        return name;
    }
}
