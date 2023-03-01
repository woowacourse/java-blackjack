package domain;

public class Player extends User {

    private static final int NAME_MAX_LENGTH = 5;
    private static final int BLACK_JACK_SCORE = 21;
    private static final String ILLEGAL_NAME = "딜러";

    private final String name;

    public Player(final String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(final String name) {
        validateBlank(name);
        validateLength(name);
        validateNotProperName(name);
    }

    private void validateNotProperName(final String name) {
        if (name.equals(ILLEGAL_NAME)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateLength(final String name) {
        if (name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean isHittable() {
        return cards.isUnder(BLACK_JACK_SCORE);
    }
}
