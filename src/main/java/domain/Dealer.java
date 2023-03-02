package domain;

import java.util.ArrayList;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    public Dealer() {
        super(new ArrayList<>(), DEALER_NAME);
    }
}
