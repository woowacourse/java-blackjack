package domain;

public final class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";

    private Dealer(final String name, final Cards cards) {
        super(name, cards);
    }

    public static Dealer create() {
        return new Dealer(DEALER_NAME, new Cards());
    }
}
