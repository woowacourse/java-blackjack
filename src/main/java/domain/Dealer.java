package domain;

public class Dealer extends Participant {

    private static final int MORE_CARD_LIMIT = 16;
    private static final Name DEALER_NAME = new Name("딜러");

    public Dealer(Cards cards) {
        super(DEALER_NAME, cards);
    }

    @Override
    public boolean isMoreCardAble() {
        return getTotalScore() <= MORE_CARD_LIMIT;
    }

    @Override
    public int getTotalScore() {
        return super.cards.calculateScore(MORE_CARD_LIMIT);
    }

}
