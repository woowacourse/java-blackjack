package blakjack.domain.participant;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;

public final class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new PrivateArea(DEALER_NAME), new Chip(0));
    }
}
