package domain.participant;

import exception.BlackjackException;

public record PlayerName(String name) {

    public static final int PLAYER_NAME_MIN_LENGTH = 2;
    public static final int PLAYER_NAME_MAX_LENGTH = 5;
    public static final String PLAYER_NAME_LENGTH_OUT_OF_RANGE =
            String.format("게임 참가자의 이름은 %d~%d글자 사이여야 합니다.", PLAYER_NAME_MIN_LENGTH, PLAYER_NAME_MAX_LENGTH);
    public static final String PLAYER_NAME_BLANK = "게임 참가자의 이름은 공백이 될 수 없습니다.";

    public PlayerName {
        validate(name);
    }

    private void validate(String name) {
        validateNotBlank(name);
        validatePlayerNameLength(name);
    }

    private void validatePlayerNameLength(String name) {
        if (!(PLAYER_NAME_MIN_LENGTH <= name.length() && name.length() <= PLAYER_NAME_MAX_LENGTH)) {
            throw new BlackjackException(PLAYER_NAME_LENGTH_OUT_OF_RANGE);
        }
    }

    private void validateNotBlank(String name) {
        if (name.isBlank()) {
            throw new BlackjackException(PLAYER_NAME_BLANK);
        }
    }
}
