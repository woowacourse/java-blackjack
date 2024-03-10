package domain.participant;

import domain.constants.CardCommand;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    private static final int CARD_PICK_THRESHOLD = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canPickCard(CardCommand cardCommand) {
        return calculateScore() > CARD_PICK_THRESHOLD;
    }
}
