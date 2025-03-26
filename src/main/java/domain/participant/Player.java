package domain.participant;

import domain.card.Card;
import java.util.List;

public class Player extends Participant {
    private static final int PICK_DECISION_VALUE = 21;

    public Player(String name) {
        super(name);
    }

    @Override
    public List<Card> getFirstShownCard() {
        return getCards();
    }

    @Override
    public boolean canPick() {
        return getTotalValue() <= PICK_DECISION_VALUE;
    }
}
