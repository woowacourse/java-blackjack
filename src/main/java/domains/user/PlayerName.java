package domains.user;

import java.util.Objects;

public class PlayerName {
    private static final String DEALER = "딜러";
    private String playerName;

    public PlayerName(String playerName) {
        checkNullOrEmpty(playerName);
        checkSameAsDealer(playerName);
        this.playerName = playerName;
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

    @Override
    public String toString() {
        return playerName;
    }
}
