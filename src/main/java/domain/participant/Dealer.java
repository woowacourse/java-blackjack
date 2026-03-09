package domain.participant;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_HIT_STANDARD = 16;

    public Dealer(Hand hand) {
        super(new PlayerName(DEALER_NAME), hand);
    }

    public boolean shouldHit() {
        return getTotalCardScore() <= DEALER_HIT_STANDARD;
    }

    public String initialHandDisplay() {
        return getHandCards().getFirst().getCardCode() + getHandCards().getFirst().getCardShape();
    }
}
