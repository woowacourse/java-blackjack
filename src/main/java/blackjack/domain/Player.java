package blackjack.domain;

public class Player extends Participant {

    private final Name name;

    public Player(final String input) {
        this.name = new Name(input);
    }
}
