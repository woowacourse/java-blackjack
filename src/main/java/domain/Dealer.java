package domain;

import java.util.List;

public class Dealer extends Participant {

    private static final int PICK_DECISION_VALUE = 16;

    public Dealer(String name) {
        super(name);
    }

    @Override
    public List<Card> getShownCard() {
        return List.of(getCards().getFirst());
    }

    @Override
    public boolean canPick() {
        return getTotalValue() <= PICK_DECISION_VALUE;
    }
}
