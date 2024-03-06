package blackjack.domain.participant;

public class Player extends Participant {

    private static final int BLACKJACK_BOUND = 21;

    public Player(final String name) {
        super(name);
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() < BLACKJACK_BOUND;
    }
}
