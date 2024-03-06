package blackjack.domain.participant;

public class Dealer extends Participant {

    private static final int DEALER_BOUND = 16;

    public Dealer(final String name) {
        super(name);
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() <= DEALER_BOUND;
    }
}
