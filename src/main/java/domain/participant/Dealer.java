package domain.participant;

public class Dealer extends Participant {
    private static final int CAN_RECEIVE_CARD_THRESHOLD = 17;
    private static final String DEALER = "딜러";

    public Dealer() {
        super(new Name(DEALER));
    }

    public boolean isContinueGame() {
        if (participantCards.calculateScore() >= CAN_RECEIVE_CARD_THRESHOLD) {
            return false;
        }
        return true;
    }
}
