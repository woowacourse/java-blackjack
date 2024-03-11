package domain.participant;

import domain.game.DecisionToContinue;

public class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";
    private static final int CARD_PICK_THRESHOLD = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canPickCard(DecisionToContinue decision) {
        return calculateScore() <= CARD_PICK_THRESHOLD;
    }
}
