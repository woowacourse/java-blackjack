package domain.participant;

public class Player extends Participant {

    private static final int LIMIT_TAKE_CARD_VALUE = 21;

    public Player(Name name, HandCards handCards) {
        super(name, handCards);
    }

    public boolean checkCardsCondition() {
        return getOptimalCardValueSum() <= LIMIT_TAKE_CARD_VALUE;
    }
}
