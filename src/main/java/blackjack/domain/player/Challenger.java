package blackjack.domain.player;

import blackjack.domain.player.exception.InvalidPlayerNameException;

public class Challenger extends Player {

    private static final String INVALID_NAME = "딜러";
    private static final int MAXIMUM_POINT = 21;

    private final String name;

    public Challenger(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.equals(INVALID_NAME)) {
            throw new InvalidPlayerNameException();
        }
    }

    @Override
    public Boolean canPick() {
        return holdingCards.sum() <= MAXIMUM_POINT;
    }

    @Override
    public String getName() {
        return name;
    }
}
