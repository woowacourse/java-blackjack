import java.util.Objects;

public class ParticipantName {
    private String participantName;

    public ParticipantName(String participantName) {
        checkNullOrEmpty(participantName);
        this.participantName = participantName;
    }

    private void checkNullOrEmpty(String userName) {
        if (Objects.isNull(userName) || userName.isEmpty()) {
            throw new InvalidParticipantNameException(InvalidParticipantNameException.NULL_OR_EMPTY);
        }
    }
}
