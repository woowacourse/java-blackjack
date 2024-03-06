package blackjack;

public class Dealer {
    private static final Integer RECEIVE_SIZE = 16;
    private static final String DEFAULT_DEALER_NAME = "딜러";

    private final Name name;
    private final Cards cards;

    public Dealer(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public static Dealer createDefaultDealer(Cards cards) {
        return new Dealer(new Name(DEFAULT_DEALER_NAME), cards);
    }

    public String getName() {
        return name.asString();
    }
}
