package domain.participant;

public class Player extends Participant {
    private static final int LIMIT_TAKE_CARD_VALUE = 21;

    public Player(Name name) {
        super(name);
    }

    public boolean isAbleToDraw() {
        return getMaxSum() > 0 && getMaxSum() <= LIMIT_TAKE_CARD_VALUE;
    }
}
