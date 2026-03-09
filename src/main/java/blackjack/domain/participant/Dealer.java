package blackjack.domain.participant;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int HIT_THRESHOLD = 16;

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    public boolean canReceiveCard() {
        return calculateScore().isLessThanOrEqualTo(HIT_THRESHOLD);
    }
}
