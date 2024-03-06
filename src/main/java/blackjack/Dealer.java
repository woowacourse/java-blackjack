package blackjack;

public class Dealer extends Player implements CardReceivable {
    private static final Integer RECEIVE_SIZE = 16;
    private static final String DEFAULT_DEALER_NAME = "딜러";


    public Dealer(Name name, Cards cards) {
        super(name, cards);
    }

    public static Dealer createDefaultDealer(Cards cards) {
        return new Dealer(new Name(DEFAULT_DEALER_NAME), cards);
    }

    @Override
    public boolean isReceivable() {
        return cards.sum() <= RECEIVE_SIZE;
    }
}
