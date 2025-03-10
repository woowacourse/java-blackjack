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
        if (cards.isAceCountEqualTo(DOUBLE_ACE_COUNT)) {
            return true;
        }
        int sumWithoutAce = cards.calculateSumWithoutAce();
        if (cards.isAceCountEqualTo(SINGLE_ACE_COUNT)) {
            return (sumWithoutAce + CardNumberType.getAceHighNumber()) <= VALID_DRAW_LIMIT;
        }
        return sumWithoutAce <= VALID_DRAW_LIMIT;
    }
}
