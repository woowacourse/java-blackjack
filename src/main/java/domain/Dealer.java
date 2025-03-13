package domain;

public class Dealer extends Participant {

    private static final int VALID_DRAW_LIMIT = 16;

    private Dealer(Hand hand) {
        super(hand);
    }

    public static Dealer createEmpty() {
        return new Dealer(Hand.createEmpty());
    }

    public Card getFirstCard() {
        return hand.getFirst();
    }

    @Override
    public boolean isPossibleDraw() {
        return hand.calculateSum() <= VALID_DRAW_LIMIT;
    }
}
