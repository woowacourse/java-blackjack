package blackjack.domain.participant;

public class Player extends Participant {

    private final Name name;

    public Player(final String input) {
        this.name = new Name(input);
    }

    public String getName() {
        return name.getValue();
    }
}
