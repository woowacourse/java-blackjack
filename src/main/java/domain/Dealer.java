package domain;

public class Dealer extends Participant {

    private static final int MORE_CARD_LIMIT = 16;
    private static final Name DEALER_NAME = new Name("딜러");

    public Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    public Card showOneCard() {
        return hand.getFirstCard();
    }

    public boolean isMoreCardAble() {
        return getTotalScore() <= MORE_CARD_LIMIT;
    }

    public int getTotalScore() {
        return hand.calculateScore(MORE_CARD_LIMIT);
    }

}
