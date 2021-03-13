package blakcjack.domain.participant;

public class Player extends Participant {
    public Player(final String name) {
        this(name, 0);
    }

    public Player(final String name, final double money) {
        super(name, money);
    }

    @Override
    public ParticipantType getType() {
        return ParticipantType.PLAYER;
    }
}
