package user;

import java.util.Objects;

public class PlayerName {
    private static final String DEALER = "딜러";
    private String participantName;

    public PlayerName(String participantName) {
        checkNullOrEmpty(participantName);
        checkSameAsDealer(participantName);
        this.participantName = participantName;
    }

    private void checkNullOrEmpty(String userName) {
        if (Objects.isNull(userName) || userName.isEmpty()) {
            throw new InvalidPlayerNameException(InvalidPlayerNameException.NULL_OR_EMPTY);
        }
    }

    private void checkSameAsDealer(String userName) {
        if (userName.equals(DEALER)) {
            throw new InvalidPlayerNameException(InvalidPlayerNameException.SAME_AS_DEALER);
        }
    }
}
