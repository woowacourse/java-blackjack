package domain;

import java.util.List;

public class Dealer extends Player {

    private static final int PICK_BOUNDARY = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new PlayerName(DEALER_NAME));
    }

    @Override
    public void initialPick() {
        pickCard();
    }

    public int getPickBoundary() {
        return PICK_BOUNDARY;
    }
}
