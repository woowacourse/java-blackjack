package domain.player;

import domain.card.Deck;
import domain.state.Hittable;
import domain.state.State;

public class Dealer extends Player {

    public static final String DEALER_NAME = "딜러";
    public static final int FORCED_HIT_THRESHOLD = 16;

    private Dealer(String name, State state) {
        super(name, state);
    }

    public static Dealer createDealer() {
        return new Dealer(DEALER_NAME, Hittable.initialDealerState());
    }

    @Override
    public void openInitialCards() {
        openCards(1);
    }

    public void hitWhileUnder16(Deck deck) {
        while (!isFinished()) {
            hit(deck);
        }
    }
}
