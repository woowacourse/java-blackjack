package domain.player;

import domain.state.Hittable;
import domain.state.State;

public class Dealer extends Player {

    public static final String DEALER_NAME = "딜러";

    private Dealer(String name, State state) {
        super(name, state);
    }

    public static Dealer createDefaultDealer() {
        return new Dealer(DEALER_NAME, Hittable.initialDealerState());
    }
}
