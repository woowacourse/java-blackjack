package model.participant;

import java.util.List;

public final class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    private static final int FIRST_TURN_CARD_COUNT = 2;

    private Dealer(String name) {
        super(name);
    }

    public static Dealer of() {
        return new Dealer(DEALER_NAME);
    }

    @Override
    public List<String> open() {
        if (hands.size() == FIRST_TURN_CARD_COUNT) {
            return List.of(hands.getFirst());
        }

        return hands.open();
    }

    @Override
    public boolean isDealer() {
        return true;
    }
}
