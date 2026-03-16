package blackjack.domain;

public class Dealer extends Participant {
    private static final int DEALER_HIT_THRESHOLD = 16;

    private Dealer(Hand hand) {
        super(hand);
    }

    public static Dealer of() {
        return new Dealer(Hand.init());
    }

    public boolean shouldHit() {
        return score() <= DEALER_HIT_THRESHOLD;
    }

    public TrumpCard getOpenCard() {
        return hand.getCards().getFirst();
    }
}