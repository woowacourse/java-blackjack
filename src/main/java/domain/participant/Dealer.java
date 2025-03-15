package domain.participant;

import domain.card.Card;
import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int PICK_DECISION_VALUE = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public List<Card> getShownCard() {
        return List.of(getFirstCard());
    }

    @Override
    public boolean canPick() {
        return getTotalValue() <= PICK_DECISION_VALUE;
    }
}
