package domain;

public class Dealer extends Participant {

    private static final int VALID_DRAW_LIMIT = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    public Dealer() {
        this(Hand.createEmpty());
    }

    public Card getDealCard() {
        return hand.getFirst();
    }

    public boolean isPossibleDraw() {
        return hand.calculateSum() <= VALID_DRAW_LIMIT;
    }
}
