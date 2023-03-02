package domain.participant;

public abstract class Participant {

    private final ParticipantName name;

    protected Participant(final String name) {
        this.name = ParticipantName.create(name);
    }
}
