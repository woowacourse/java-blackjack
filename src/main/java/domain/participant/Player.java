package domain.participant;

public class Player extends Participant{

    private Player(ParticipantName name) {
        super(name);
    }

    public static Player from(ParticipantName name) {
        return new Player(name);
    }
}
