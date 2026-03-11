package domain.state;

import domain.hitStrategy.HitStrategy;
import domain.participants.Hand;
import domain.participants.Participant;

public class Hit extends State {
    private final HitStrategy hitStrategy;

    public Hit(Hand hand, Participant participant, HitStrategy hitStrategy) {
        super(hand, participant);
        this.hitStrategy = hitStrategy;
    }

    @Override
    public boolean isFinished() {
        return !hitStrategy.canHit(this);
    }
}
