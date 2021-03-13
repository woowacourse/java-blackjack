package blakcjack.domain.participant;

public class Player extends Participant {
    public Player(final String name) {
        super(name);
    }

    @Override
    public ParticipantType getType() {
        return ParticipantType.PLAYER;
    }

    @Override
    public boolean supports(final ParticipantType participantType) {
        return ParticipantType.PLAYER.equals(participantType);
    }
}
