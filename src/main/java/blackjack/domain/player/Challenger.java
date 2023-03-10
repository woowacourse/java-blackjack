package blackjack.domain.player;

import blackjack.domain.player.exception.InvalidPlayerNameException;

public class Challenger extends Player {

    private static final String INVALID_NAME = "딜러";
    private static final int MAXIMUM_POINT_TO_PICK = 21;

    private final String name;

    public Challenger(final String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(final String name) {
        if (name.equals(INVALID_NAME)) {
            throw new InvalidPlayerNameException();
        }
    }

    @Override
    public Boolean canPick() {
        Score score = holdingCards.getDefaultSum();
        return score.getValue() <= MAXIMUM_POINT_TO_PICK;
    }

    @Override
    public Boolean isDealer() {
        return false;
    }

    @Override
    public Boolean isChallenger() {
        return true;
    }

    @Override
    public String getName() {
        return name;
    }
}
