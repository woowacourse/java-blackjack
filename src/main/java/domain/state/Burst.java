package domain.state;

import domain.card.Hand;
import domain.participants.Participant;

public class Burst extends State {

    public Burst(Hand hand, Participant participant) {
        super(hand, participant);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

