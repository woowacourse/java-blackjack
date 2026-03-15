package domain.participant;

import domain.betiing.BetAmount;
import java.util.Objects;

public class Player extends Participant{

    private final BetAmount betAmount;

    private Player(ParticipantName name, BetAmount betAmount) {
        super(name);
        this.betAmount = betAmount;
    }

    public static Player from(ParticipantName participantName) {
        return new Player(participantName, BetAmount.from(0));
    }

    public static Player from(ParticipantName name, BetAmount betAmount) {
        return new Player(name, betAmount);
    }

    public BetAmount getBetAmount() {
        return betAmount;
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
