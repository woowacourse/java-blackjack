package domain.participant;

public class Player extends Participant {

    private static final int LIMIT_HIT_VALUE = 21;

    public Player(Name name, HandCards handCards) {
        super(name, handCards);
    }

    public boolean canHit() {
        return handCards.calculateOptimalCardValueSum() <= LIMIT_HIT_VALUE;
    }
}
