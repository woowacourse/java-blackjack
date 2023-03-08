package domain;

public class Dealer extends Participant {

    private static final Score MORE_CARD_LIMIT = new Score(16);
    private static final Name DEALER_NAME = new Name("딜러");

    public Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    public Card showOneCard() {
        return hand.getFirstCard();
    }

    public boolean isMoreCardAble() {
        return getTotalScore().isLessThanOrEqual(MORE_CARD_LIMIT);
    }

    public Score getTotalScore() {
        return hand.calculateScore(MORE_CARD_LIMIT);
    }

}
