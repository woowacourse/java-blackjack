package domain;

public class Dealer {
    private final Hand hand;

    private Dealer(final Hand hand) {
        this.hand = hand;
    }

    public static Dealer init() {
        return new Dealer(Hand.init());
    }
}
