package model.participant;

import java.util.List;

public final class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";

    private Dealer(String name) {
        super(name);
    }

    public static Dealer of() {
        return new Dealer(DEALER_NAME);
    }

    @Override
    public List<String> open() {
        return List.of(hands.getFirst());
    }

    @Override
    public List<String> openAll() {
        return hands.open();
    }

    @Override
    public int calculateRevenue(int dealerScore, boolean dealerBust, boolean dealerBlackJack) {
        return 0;
    }

    @Override
    public boolean isDealer() {
        return true;
    }
}
