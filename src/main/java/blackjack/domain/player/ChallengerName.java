package blackjack.domain.player;

import blackjack.domain.player.exception.InvalidPlayerNameException;

public class ChallengerName {

    private static final String INVALID_NAME = "딜러";

    private final String name;

    public ChallengerName(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(final String name) {
        if (name.equals(INVALID_NAME)) {
            throw new InvalidPlayerNameException();
        }
    }

    public String getName() {
        return name;
    }
}
