package domain.participant;

public class Player extends Participant {

    public Player(ParticipantName name) {
        super(name);
    }

    @Override
    boolean isDrawable() {
        Score totalScore = hand.calculateCardSum();
        return !hand.isBust(totalScore);
    }
}
