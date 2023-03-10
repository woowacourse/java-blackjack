package domain.participant;

public class Player extends Participant {

    private static final int LIMIT_HIT_VALUE = 21;

    public Player(Name name, Hand hand) {
        super(name, hand);
    }

    public boolean canHit() {
        return this.calculateOptimalCardValueSum() <= LIMIT_HIT_VALUE;
    }
}
