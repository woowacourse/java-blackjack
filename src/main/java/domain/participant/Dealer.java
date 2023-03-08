package domain.participant;

import util.Constants;

public class Dealer extends Participant {
    private static final int LIMIT_TAKE_CARD_VALUE = 17;

    public Dealer(HandCards handCards) {
        super(new Name(Constants.DEALER_NAME), handCards);
    }

    public boolean checkCardsCondition() {
        return selectNotBustCardValueSum() < LIMIT_TAKE_CARD_VALUE;
    }
}
