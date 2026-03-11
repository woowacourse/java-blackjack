package domain.participant;

import java.util.Objects;

public class Player extends Participant{

    private Player(ParticipantName name) {
        super(name);
    }

    public static Player from(ParticipantName name) {
        return new Player(name);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(name, that.name) && Objects.equals(hand, that.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hand);
    }
}
