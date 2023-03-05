package domain.participant;

public class Dealer extends Participant {
    private static final int LIMIT_TAKE_CARD_VALUE = 17;
    public static final String DEALER_NAME = "딜러";

    public Dealer(HandCards handCards) {
        super(new Name(DEALER_NAME), handCards);
    }

    public boolean checkCardsCondition() {
        return getOptimalSumValue() < LIMIT_TAKE_CARD_VALUE;
    }
}
