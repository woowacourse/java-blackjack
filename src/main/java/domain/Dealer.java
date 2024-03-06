package domain;

public class Dealer implements Participant {
    private static final int MAX_DEALER_HAND_VALUE = 16;

    private final Hand hand;

    Dealer(final Hand hand) {
        this.hand = hand;
    }

    public static Dealer init() {
        return new Dealer(Hand.init());
    }

    @Override
    public boolean isDrawable() {
        return hand.getCardsNumberSum() <= MAX_DEALER_HAND_VALUE;
    }

    @Override
    public void draw(final Deck deck) {
        hand.addCard(deck.drawn());
    }
}
