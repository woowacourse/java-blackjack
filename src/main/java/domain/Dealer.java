package domain;

public class Dealer extends Participant {

    private static final Score MORE_CARD_LIMIT = new Score(16);

    public Dealer(Hand hand) {
        super(Name.DEALER, hand);
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
