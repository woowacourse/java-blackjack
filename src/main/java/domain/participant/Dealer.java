package domain.participant;

public class Dealer extends Participant {

    private static final int DEALER_ADDITIONAL_DRAW_CONDITION = 16;
    private static final String DEALER_DISPLAY_NAME = "딜러";

    private Dealer() {
        super(ParticipantName.from(DEALER_DISPLAY_NAME));
    }

    public static Dealer create() {
        return new Dealer();
    }

    public boolean canHit() {
        return hand.getBasicScore() <= DEALER_ADDITIONAL_DRAW_CONDITION;
    }

}
