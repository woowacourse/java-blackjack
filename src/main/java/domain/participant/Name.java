package domain.participant;

public record Name(
        String name
) {
    private static final String PLAYER_NAME_LENGTH_ERROR = "플레이어의 이름은 2글자 이상 10글자 이하여야 합니다.";

    public static final int MINIMUM_BOUND = 2;
    public static final int MAXIMUM_BOUND = 10;

    public Name {
        validateNameLength(name);
    }


    private void validateNameLength(final String name) {
        if ((name.length() < MINIMUM_BOUND) || (name.length() > MAXIMUM_BOUND)) {
            throw new IllegalArgumentException(PLAYER_NAME_LENGTH_ERROR);
        }
    }
}
