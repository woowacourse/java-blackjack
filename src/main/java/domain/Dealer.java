package domain;

public class Dealer extends Participant {

    private static final int VALID_DRAW_LIMIT = 16;
    private static final int DOUBLE_ACE_COUNT = 2;
    private static final int SINGLE_ACE_COUNT = 1;

    private Dealer(Cards cards) {
        super(cards);
    }

    public static Dealer createEmpty() {
        return new Dealer(Cards.createEmpty());
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public boolean isPossibleDraw() {
        return cards.calculateSum() <= VALID_DRAW_LIMIT;
    }
}
