package domain;

public class Dealer extends Participant {

    private static final int VALID_DRAW_LIMIT = 16;

    private String name;

    private Dealer(Hand hand) {
        super(hand);
    }

    public static Dealer createEmpty() {
        return new Dealer(Hand.createEmpty());
    }

    public Card getDealCard() {
        return hand.getFirst();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isPossibleDraw() {
        return hand.calculateSum() <= VALID_DRAW_LIMIT;
    }
}
