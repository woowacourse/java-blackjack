package exception;

public enum ErrorMessage {
    PLAYER_NAME_LENGTH_ERROR("플레이어의 이름은 2글자 이상 10글자 이하여야 합니다."),
    INPUT_EMPTY_ERROR("빈 값을 입력할 수 없습니다."),
    INVALID_HIT_STAND_INPUT_ERROR("y 또는 n만 입력 가능합니다."),
    DEALER_NOT_FOUND_ERROR("딜러가 존재하지 않습니다."),
    PLAYER_COUNT_OUT_OF_RANGE("참여자 수 제한에 맞지 않습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
