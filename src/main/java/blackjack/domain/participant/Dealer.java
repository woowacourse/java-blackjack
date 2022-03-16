package blackjack.domain.participant;

public class Dealer extends Participant {
    private static final int DEALER_MIN_TOTAL = 17;
    private static final String DEALER = "딜러";

    public Dealer() {
        super(DEALER);
    }

    @Override
    public boolean isFinished() {
        return holdingCard.computeTotalScore() >= DEALER_MIN_TOTAL;
    }

}
