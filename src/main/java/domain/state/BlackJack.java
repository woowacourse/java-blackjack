package domain.state;

import domain.participants.Hand;
import domain.participants.Participant;

public class BlackJack extends State {

    protected BlackJack(Hand hand, Participant participant) {
        super(hand, participant);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
