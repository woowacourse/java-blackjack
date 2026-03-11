package domain.state;

import domain.participants.Hand;
import domain.participants.Participant;

public class Stay extends State {
    public Stay(Hand hand, Participant participant) {
        super(hand, participant);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

