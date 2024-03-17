package blackjack.domain.player;

public class Player extends Participant {

    private Player(final Hand hand, final Name name) {
        super(hand, name);
    }

    public Player(final Name name) {
        this(new Hand(), name);
    }
}
