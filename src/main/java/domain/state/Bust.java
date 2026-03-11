package domain.state;

import domain.participants.Hand;
import domain.participants.Participant;

public class Bust extends State {

    public Bust(Hand hand, Participant participant) {
        super(hand, participant);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
