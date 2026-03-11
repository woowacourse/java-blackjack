package domain;

import static exception.ErrorMessage.PLAYER_NAME_LENGTH_ERROR;

public record Name(
        String name
) {
    public static final int MINIMUM_BOUND = 2;
    public static final int MAXIMUM_BOUND = 10;

    // TODO: 검증 테스트 추가
    public Name(final String name) {
        if ((name.length() < MINIMUM_BOUND) || (name.length() > MAXIMUM_BOUND)) {
            throw new IllegalArgumentException(PLAYER_NAME_LENGTH_ERROR.getMessage());
        }
        this.name = name;
    }
}
