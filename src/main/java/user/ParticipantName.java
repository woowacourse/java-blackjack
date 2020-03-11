package user;

import java.util.Objects;

public class ParticipantName {
    private static final String DEALER = "딜러";
    private String participantName;

    public ParticipantName(String participantName) {
        checkNullOrEmpty(participantName);
        checkSameAsDealer(participantName);
        this.participantName = participantName;
    }

    private void checkNullOrEmpty(String userName) {
        if (Objects.isNull(userName) || userName.isEmpty()) {
            throw new InvalidParticipantNameException(InvalidParticipantNameException.NULL_OR_EMPTY);
        }
    }

    private void checkSameAsDealer(String userName) {
        if (userName.equals(DEALER)) {
            throw new InvalidParticipantNameException(InvalidParticipantNameException.SAME_AS_DEALER);
        }
    }
}
